package jweblite.web.wrapper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;
import jweblite.web.wrapper.stream.JWebLiteServletResponseWrapperStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteResponseWrapper extends HttpServletResponseWrapper {

	private Log log = LogFactory.getLog(this.getClass());

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
		this.setEncoding(encoding);
		String acceptContentEncoding = null;
		this.isGZipAccepted = (req != null
				&& (acceptContentEncoding = req.getHeader("Accept-Encoding")) != null && acceptContentEncoding
				.indexOf("gzip") >= 0);
		this.setGZipEnabled(isGZipEnabled);
		// init
		resp.setHeader("Implementation-Title", "jweblite");
		this.wrapperStream = new JWebLiteServletResponseWrapperStream(
				super.getOutputStream());
	}

	/**
	 * Default constructor.
	 * 
	 * @param resp
	 *            HttpServletResponse
	 * @throws IOException
	 */
	public JWebLiteResponseWrapper(HttpServletResponse resp) throws IOException {
		this(null, resp, null, true);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.wrapperStream == null) {
			throw new IllegalStateException();
		}
		return this.wrapperStream.getOutputStream(this.isGZipEnabled);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (this.wrapperStream == null) {
			throw new IllegalStateException();
		}
		return this.wrapperStream.getWriter(this.isGZipEnabled, this.encoding);
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		this.reset();
		super.sendError(sc, msg);
	}

	@Override
	public void sendError(int sc) throws IOException {
		this.reset();
		super.sendError(sc);
	}

	/**
	 * Do Finish
	 * 
	 * @throws IOException
	 */
	public void doFinish() throws IOException {
		if (this.wrapperStream == null) {
			throw new IllegalStateException();
		}
		this.wrapperStream.doFinish(this.isGZipEnabled);
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
			this.setCharacterEncoding(encoding);
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
		if (!this.isGZipAccepted) {
			isGZipEnabled = false;
		}
		this.isGZipEnabled = isGZipEnabled;
		this.setHeader("Content-Encoding", (isGZipEnabled ? "gzip" : null));
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