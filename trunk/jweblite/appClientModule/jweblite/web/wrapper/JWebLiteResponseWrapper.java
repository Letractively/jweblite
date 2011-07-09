package jweblite.web.wrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import jweblite.web.stream.GZipServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteResponseWrapper extends HttpServletResponseWrapper {

	private Log log = LogFactory.getLog(this.getClass());

	private String encoding = null;
	private boolean isGZipEnabled = false;

	private final boolean isAcceptGZip;
	private GZipServletOutputStream gos = null;
	private PrintWriter gpw = null;

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
	 */
	public JWebLiteResponseWrapper(HttpServletRequest req,
			HttpServletResponse resp, String encoding, boolean isGZipEnabled) {
		super(resp);
		this.setEncoding(encoding);
		String acceptContentEncoding = null;
		this.isAcceptGZip = (req != null
				&& (acceptContentEncoding = req.getHeader("Accept-Encoding")) != null && acceptContentEncoding
				.indexOf("gzip") >= 0);
		this.setGZipEnabled(isGZipEnabled);
		// init
		resp.setHeader("Implementation-Title", "jweblite");
	}

	/**
	 * Default constructor.
	 * 
	 * @param resp
	 *            HttpServletResponse
	 */
	public JWebLiteResponseWrapper(HttpServletResponse resp) {
		this(null, resp, null, true);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (!this.isGZipEnabled) {
			return super.getOutputStream();
		}
		if (this.gpw != null) {
			throw new IllegalStateException();
		}
		if (this.gos == null) {
			this.gos = new GZipServletOutputStream(super.getOutputStream());
		}
		return this.gos;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (!this.isGZipEnabled) {
			return super.getWriter();
		}
		if (this.gos != null) {
			throw new IllegalStateException();
		}
		if (this.gpw == null) {
			this.gpw = new PrintWriter(new OutputStreamWriter(
					new GZipServletOutputStream(super.getOutputStream()),
					this.encoding));
		}
		return this.gpw;
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
		if (this.isGZipEnabled) {
			if (this.gos != null) {
				this.gos.close();
			}
			if (this.gpw != null) {
				this.gpw.close();
			}
		}
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
		if (!this.isAcceptGZip) {
			return;
		}
		this.isGZipEnabled = isGZipEnabled;
		this.setHeader("Content-Encoding", (isGZipEnabled ? "gzip" : null));
	}

}