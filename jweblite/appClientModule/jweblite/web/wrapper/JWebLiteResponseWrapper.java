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

	private String encoding;
	private boolean isGZipEnabled;

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
		if (req != null) {
			String acceptContentEncoding = req.getHeader("Accept-Encoding");
			if (acceptContentEncoding == null
					|| acceptContentEncoding.indexOf("gzip") < 0) {
				isGZipEnabled = false;
			}
		}
		this.isGZipEnabled = isGZipEnabled;
		// init
		resp.setHeader("Implementation-Title", "jweblite");
		if (isGZipEnabled) {
			this.setHeader("Content-Encoding", "gzip");
		}
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
		this.isGZipEnabled = isGZipEnabled;
	}

}