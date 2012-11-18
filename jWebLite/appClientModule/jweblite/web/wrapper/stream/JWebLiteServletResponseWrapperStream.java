package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.GZipServletOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteServletResponseWrapperStream implements
		JWebLiteResponseWrapperStream {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory
			.getLog(JWebLiteServletResponseWrapperStream.class);

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
		if (!isGZipEnabled) {
			return this.outputStream;
		}
		if (this.pw != null) {
			throw new IllegalStateException();
		}
		if (this.sos == null) {
			this.sos = new GZipServletOutputStream(this.outputStream);
		}
		return this.sos;
	}

	public PrintWriter getWriter(boolean isGZipEnabled, String encoding)
			throws IOException {
		if (!isGZipEnabled) {
			return new PrintWriter(new OutputStreamWriter(this.outputStream,
					encoding));
		}
		if (this.sos != null) {
			throw new IllegalStateException();
		}
		if (this.pw == null) {
			this.pw = new PrintWriter(new OutputStreamWriter(
					new GZipServletOutputStream(this.outputStream), encoding));
		}
		return this.pw;
	}

	public void doFinish(boolean isGZipEnabled) {
		if (isGZipEnabled) {
			if (this.sos != null) {
				IOUtils.closeQuietly(this.sos);
			}
			if (this.pw != null) {
				IOUtils.closeQuietly(this.pw);
			}
		}
	}

}