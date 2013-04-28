package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.GZipServletOutputStream;

import org.apache.commons.io.IOUtils;

public class JWebLiteServletResponseWrapperStream implements
		JWebLiteResponseWrapperStream {
	private static final long serialVersionUID = 1L;

	private ServletOutputStream outputStream = null;

	private ServletOutputStream sos = null;
	private PrintWriter pw = null;

	/**
	 * Default constructor.
	 * 
	 * @param outputStream
	 *            ServletOutputStream
	 */
	public JWebLiteServletResponseWrapperStream(ServletOutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	public ServletOutputStream getOutputStream(boolean isGZipEnabled)
			throws IOException {
		if (this.pw != null) {
			throw new IllegalStateException();
		}
		if (this.sos == null) {
			if (isGZipEnabled) {
				this.sos = new GZipServletOutputStream(this.outputStream);
			} else {
				this.sos = this.outputStream;
			}
		}
		return this.sos;
	}

	public PrintWriter getWriter(boolean isGZipEnabled, String encoding)
			throws IOException {
		if (this.sos != null) {
			throw new IllegalStateException();
		}
		if (this.pw == null) {
			ServletOutputStream sos = this.outputStream;
			if (isGZipEnabled) {
				sos = new GZipServletOutputStream(this.outputStream);
			}
			this.pw = new PrintWriter(new OutputStreamWriter(sos, encoding));
		}
		return this.pw;
	}

	public void doFinish(boolean isGZipEnabled) {
		if (this.sos != null) {
			IOUtils.closeQuietly(this.sos);
		}
		if (this.pw != null) {
			IOUtils.closeQuietly(this.pw);
		}
	}

}