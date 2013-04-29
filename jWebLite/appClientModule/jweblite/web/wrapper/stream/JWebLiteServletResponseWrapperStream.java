package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.GZipServletOutputStream;
import jweblite.web.stream.LineFilteredOutputStreamEvent;
import jweblite.web.stream.LineFilteredOutputStreamWriter;
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

	private LineFilteredOutputStreamEvent lineFilteredOutputStreamEvent = null;

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
			if (lineFilteredOutputStreamEvent == null) {
				osw = new OutputStreamWriter(sos, this.encoding);
			} else {
				osw = new LineFilteredOutputStreamWriter(sos, this.encoding) {
					@Override
					public void doInit() throws IOException {
						super.doInit();
						lineFilteredOutputStreamEvent.doInit(this);
					}

					@Override
					public String doBeforeRenderLine(String line)
							throws IOException {
						line = super.doBeforeRenderLine(line);
						return lineFilteredOutputStreamEvent
								.doBeforeRenderLine(this, line);
					}

					@Override
					public void doAfterRenderLine(String line)
							throws IOException {
						super.doAfterRenderLine(line);
						lineFilteredOutputStreamEvent.doAfterRenderLine(this,
								line);
					}

					@Override
					public void doFinish() throws IOException {
						super.doFinish();
						lineFilteredOutputStreamEvent.doFinish(this);
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

	public void setLineFilteredOutputStreamEvent(
			LineFilteredOutputStreamEvent lineFilteredOutputStreamEvent) {
		this.lineFilteredOutputStreamEvent = lineFilteredOutputStreamEvent;
		resetOutputStream(this.os);
	}

	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	public void setGZipEnabled(boolean isGZipEnabled) {
		this.isGZipEnabled = isGZipEnabled;
	}

}