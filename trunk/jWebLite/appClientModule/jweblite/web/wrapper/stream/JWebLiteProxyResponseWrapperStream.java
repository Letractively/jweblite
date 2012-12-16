package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.ProxyServletOutputStream;

import org.apache.commons.io.IOUtils;

public class JWebLiteProxyResponseWrapperStream implements
		JWebLiteResponseWrapperStream {
	private static final long serialVersionUID = 1L;

	private OutputStream outputStream = null;

	private ServletOutputStream sos = null;
	private PrintWriter pw = null;

	/**
	 * Default constructor.
	 * 
	 * @param outputStream
	 *            OutputStream
	 */
	public JWebLiteProxyResponseWrapperStream(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	public ServletOutputStream getOutputStream(boolean isGZipEnabled)
			throws IOException {
		if (this.pw != null) {
			throw new IllegalStateException();
		}
		if (this.sos == null) {
			this.sos = new ProxyServletOutputStream(this.outputStream);
		}
		return this.sos;
	}

	public PrintWriter getWriter(boolean isGZipEnabled, String encoding)
			throws IOException {
		if (this.sos != null) {
			throw new IllegalStateException();
		}
		if (this.pw == null) {
			this.pw = new PrintWriter(new OutputStreamWriter(
					new ProxyServletOutputStream(this.outputStream), encoding));
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