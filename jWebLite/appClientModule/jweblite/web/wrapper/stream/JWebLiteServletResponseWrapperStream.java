package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
		if (this.pw != null) {
			throw new IllegalStateException();
		}
		if (this.sos == null) {
			if (this.isGZipEnabled) {
				this.sos = new GZipServletOutputStream(this.os);
			} else if (this.os instanceof ServletOutputStream) {
				this.sos = (ServletOutputStream) this.os;
			} else {
				this.sos = new ProxyServletOutputStream(this.os);
			}
		}
		return this.sos;
	}

	public PrintWriter getServletWriter() throws IOException {
		if (this.sos != null) {
			throw new IllegalStateException();
		}
		if (this.pw == null) {
			OutputStream sos = this.os;
			if (this.isGZipEnabled) {
				sos = new GZipServletOutputStream(this.os);
			}
			OutputStreamWriter osw = null;
			if (lineWriterListener == null) {
				osw = new OutputStreamWriter(sos, this.encoding);
			} else {
				osw = new LineFilteredOutputStreamWriter(sos, this.encoding) {
					@Override
					public void doInit() throws IOException {
						super.doInit();
						lineWriterListener.doInit(this);
					}

					@Override
					public String doBeforeLine(String line) throws IOException {
						line = super.doBeforeLine(line);
						return lineWriterListener.doBeforeLine(this, line);
					}

					@Override
					public void doAfterLine(String line) throws IOException {
						super.doAfterLine(line);
						lineWriterListener.doAfterLine(this, line);
					}

					@Override
					public void doFinish() throws IOException {
						super.doFinish();
						lineWriterListener.doFinish(this);
					}
				};
			}
			this.pw = new PrintWriter(osw);
		}
		return this.pw;
	}

	public void doFinish() {
		if (this.sos != null) {
			IOUtils.closeQuietly(this.sos);
		}
		if (this.pw != null) {
			IOUtils.closeQuietly(this.pw);
		}
	}

	public OutputStream getOriginalOutputStream() {
		return this.os;
	}

	public void resetOutputStream(OutputStream os) {
		synchronized (this) {
			this.sos = null;
			this.pw = null;
			this.os = os;
		}
	}

	public void bindLineWriterListener(LineWriterListener lineWriterListener) {
		this.lineWriterListener = lineWriterListener;
		resetOutputStream(this.os);
	}

	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	public void setGZipEnabled(boolean isGZipEnabled) {
		this.isGZipEnabled = isGZipEnabled;
	}

}