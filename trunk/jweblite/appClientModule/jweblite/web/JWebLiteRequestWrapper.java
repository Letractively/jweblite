package jweblite.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class JWebLiteRequestWrapper extends HttpServletRequestWrapper {

	private String encoding = null;
	private boolean isGetMethod = true;

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 */
	public JWebLiteRequestWrapper(HttpServletRequest req) {
		super(req);
		this.isGetMethod = ("GET".equalsIgnoreCase(req.getMethod()));
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param encoding
	 *            String
	 */
	public JWebLiteRequestWrapper(HttpServletRequest req, String encoding)
			throws UnsupportedEncodingException {
		this(req);
		this.encoding = encoding;
		this.setCharacterEncoding(this.encoding);
	}

	@Override
	public String getParameter(String name) {
		String result = super.getParameter(name);
		if (result != null && this.isGetMethod && this.encoding != null) {
			try {
				return new String(result.getBytes("ISO-8859-1"), this.encoding);
			} catch (Exception e) {
			}
		}
		return result;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] result = super.getParameterValues(name);
		if (result != null && this.isGetMethod && this.encoding != null) {
			for (int i = 0; i < result.length; i++) {
				String value = result[i];
				if (value == null) {
					continue;
				}
				try {
					result[i] = new String(value.getBytes("ISO-8859-1"),
							this.encoding);
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

}