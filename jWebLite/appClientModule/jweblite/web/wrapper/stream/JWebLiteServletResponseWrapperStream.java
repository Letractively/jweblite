package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.GZipServletOutputStream;
import jweblite.web.stream.LineFilteredOutputStreamWriter;
import jweblite.web.stream.LineWriterListener;
import jweblite.web.stream.ProxyServletOutputStream;

import org.apache.commons.io.IOUtils;

public class JWebLiteServletResponseWrapperStream implements
		JWebLiteResponseWrapperStream {
	private static final long serialVersionUID = 1L;

	private OutputStream os = null;
	private String encoding = null;
	private boolean isGZipEnabled = false;

	private ServletOutputStream sos = null;
	private PrintWriter pw = null;

	private List<LineWriterListener> lineWriterListeners = new ArrayList<LineWriterListener>();

	/**
	 * Default constructor.
	 * 
	 * @param os
	 *            OutputStream
	 * @param encoding
	 *            String
	 * @param isGZipEnabled
	 *            boolean
	 */
	public JWebLiteServletResponseWrapperStream(OutputStream os,
			String encoding, boolean isGZipEnabled) {
		super();
		this.os = os;
		this.encoding = encoding;
		this.isGZipEnabled = isGZipEnabled;
	}

	public ServletOutputStream getServletOutputStream() throws IOException {
		if (pw != null) {
			throw new IllegalStateException();
		}
		if (sos == null) {
			if (isGZipEnabled) {
				sos = new GZipServletOutputStream(os);
			} else if (os instanceof ServletOutputStream) {
				sos = (ServletOutputStream) os;
			} else {
				sos = new ProxyServletOutputStream(os);
			}
		}
		return sos;
	}

	public PrintWriter getServletWriter() throws IOException {
		if (sos != null) {
			throw new IllegalStateException();
		}
		if (pw == null) {
			OutputStream sos = os;
			if (isGZipEnabled) {
				sos = new GZipServletOutputStream(os);
			}
			OutputStreamWriter osw = null;
			if (lineWriterListeners == null || lineWriterListeners.size() <= 0) {
				osw = new OutputStreamWriter(sos, encoding);
			} else {
				osw = new LineFilteredOutputStreamWriter(sos, encoding) {
					@Override
					public void doInit() throws IOException {
						super.doInit();
						for (LineWriterListener lineWriterListener : lineWriterListeners) {
							lineWriterListener.doInit(this, getLineIndex());
						}
					}

					@Override
					public String doBeforeLine(String line) throws IOException {
						line = super.doBeforeLine(line);
						for (LineWriterListener lineWriterListener : lineWriterListeners) {
							line = lineWriterListener.doBeforeLine(this, line,
									getLineIndex());
						}
						return line;
					}

					@Override
					public void doAfterLine(String line) throws IOException {
						super.doAfterLine(line);
						for (LineWriterListener lineWriterListener : lineWriterListeners) {
							lineWriterListener.doAfterLine(this, line,
									getLineIndex());
						}
					}

					@Override
					public void doFinish() throws IOException {
						super.doFinish();
						for (LineWriterListener lineWriterListener : lineWriterListeners) {
							lineWriterListener.doFinish(this, getLineIndex());
						}
					}
				};
			}
			pw = new PrintWriter(osw);
		}
		return pw;
	}

	public void doFinish() {
		if (sos != null) {
			IOUtils.closeQuietly(sos);
		}
		if (pw != null) {
			IOUtils.closeQuietly(pw);
		}
	}

	public OutputStream getOriginalOutputStream() {
		return os;
	}

	public void resetOutputStream(OutputStream os) {
		synchronized (this) {
			sos = null;
			pw = null;
			this.os = os;
		}
	}

	public void bindLineWriterListener(LineWriterListener lineWriterListener) {
		if (lineWriterListeners == null) {
			throw new IllegalStateException();
		}
		lineWriterListeners.add(lineWriterListener);
		resetOutputStream(os);
	}

	public void unbindLineWriterListener(LineWriterListener lineWriterListener) {
		if (lineWriterListeners == null) {
			throw new IllegalStateException();
		}
		lineWriterListeners.remove(lineWriterListener);
		resetOutputStream(os);
	}

	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	public void setGZipEnabled(boolean isGZipEnabled) {
		this.isGZipEnabled = isGZipEnabled;
	}

}