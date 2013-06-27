package jweblite.web.wrapper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;
import jweblite.web.wrapper.stream.JWebLiteServletResponseWrapperStream;

public class JWebLiteResponseWrapper extends HttpServletResponseWrapper {

	private String encoding = null;
	private boolean isGZipEnabled = false;

	private final boolean isGZipAccepted;
	private JWebLiteResponseWrapperStream wrapperStream = null;

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param encoding
	 *            String
	 * @param isGZipEnabled
	 *            boolean
	 * @throws IOException
	 */
	public JWebLiteResponseWrapper(HttpServletRequest req,
			HttpServletResponse resp, String encoding, boolean isGZipEnabled)
			throws IOException {
		super(resp);
		this.encoding = encoding;
		this.isGZipEnabled = isGZipEnabled;
		String acceptContentEncoding = null;
		isGZipAccepted = (req != null
				&& (acceptContentEncoding = req.getHeader("Accept-Encoding")) != null && acceptContentEncoding
				.indexOf("gzip") >= 0);

		setEncoding(encoding);
		setGZipEnabled(isGZipEnabled);
		isGZipEnabled = this.isGZipEnabled;
		// init
		resp.setHeader("Implementation-Title", "jweblite");
		wrapperStream = new JWebLiteServletResponseWrapperStream(
				super.getOutputStream(), encoding, isGZipEnabled);
	}

	/**
	 * Default constructor.
	 * 
	 * @param resp
	 *            HttpServletResponse
	 * @param encoding
	 *            String
	 * @throws IOException
	 */
	public JWebLiteResponseWrapper(HttpServletResponse resp, String encoding)
			throws IOException {
		this(null, resp, encoding, false);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (wrapperStream == null) {
			throw new IllegalStateException();
		}
		return wrapperStream.getServletOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (wrapperStream == null) {
			throw new IllegalStateException();
		}
		return wrapperStream.getServletWriter();
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		reset();
		super.sendError(sc, msg);
	}

	@Override
	public void sendError(int sc) throws IOException {
		reset();
		super.sendError(sc);
	}

	/**
	 * Do Finish
	 * 
	 * @throws IOException
	 */
	public void doFinish() throws IOException {
		if (wrapperStream == null) {
			throw new IllegalStateException();
		}
		wrapperStream.close();
	}

	/**
	 * Get Encoding
	 * 
	 * @return String
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Set Encoding
	 * 
	 * @param encoding
	 *            String
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
		if (encoding != null) {
			setCharacterEncoding(encoding);
		}
	}

	/**
	 * Is GZip Enabled
	 * 
	 * @return boolean
	 */
	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	/**
	 * Set GZip Enabled
	 * 
	 * @param isGZipEnabled
	 *            boolean
	 */
	public void setGZipEnabled(boolean isGZipEnabled) {
		if (!isGZipAccepted) {
			isGZipEnabled = false;
		}
		this.isGZipEnabled = isGZipEnabled;
		setHeader("Content-Encoding", (isGZipEnabled ? "gzip" : null));
	}

	/**
	 * Is GZip Accepted
	 * 
	 * @return boolean
	 */
	public boolean isGZipAccepted() {
		return isGZipAccepted;
	}

	/**
	 * Get Wrapper Stream
	 * 
	 * @return JWebLiteResponseWrapperStream
	 */
	public JWebLiteResponseWrapperStream getWrapperStream() {
		return wrapperStream;
	}

	/**
	 * Set Wrapper Stream
	 * 
	 * @param wrapperStream
	 *            JWebLiteResponseWrapperStream
	 */
	public void setWrapperStream(JWebLiteResponseWrapperStream wrapperStream) {
		this.wrapperStream = wrapperStream;
	}

}