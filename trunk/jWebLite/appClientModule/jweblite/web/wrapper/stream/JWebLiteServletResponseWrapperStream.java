package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

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

	private LineWriterListener lineWriterListener = null;

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
			if (lineWriterListener == null) {
				osw = new OutputStreamWriter(sos, encoding);
			} else {
				osw = new LineFilteredOutputStreamWriter(sos, encoding) {
					@Override
					public void onFirstLine(Writer writer) throws IOException {
						lineWriterListener.onFirstLine(writer);
					}

					@Override
					public void onBeforeLine(Writer writer, int index,
							String line) throws IOException {
						lineWriterListener.onBeforeLine(writer, index, line);
					}

					@Override
					public void onAfterLine(Writer writer, int index,
							String line) throws IOException {
						lineWriterListener.onAfterLine(writer, index, line);
					}

					@Override
					public void onLastLine(Writer writer) throws IOException {
						lineWriterListener.onLastLine(writer);
					}
				};
			}
			pw = new PrintWriter(osw);
		}
		return pw;
	}

	public void close() throws IOException {
		if (sos != null) {
			IOUtils.closeQuietly(sos);
		}
		if (pw != null) {
			IOUtils.closeQuietly(pw);
		}
	}

	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	public void setGZipEnabled(boolean isGZipEnabled) {
		this.isGZipEnabled = isGZipEnabled;
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

	public LineWriterListener getLineWriterListener() {
		return lineWriterListener;
	}

	public void setLineWriterListener(LineWriterListener lineWriterListener) {
		this.lineWriterListener = lineWriterListener;
	}

}