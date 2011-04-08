package jweblite.web;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

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
	public Map getParameterMap() {
		Map result = super.getParameterMap();
		if (result != null && this.isGetMethod && this.encoding != null) {
			Iterator it = result.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String[] value = (String[]) result.get(key);
				if (value == null) {
					continue;
				}
				for (int i = 0; i < value.length; i++) {
					String childValue = value[i];
					if (childValue == null) {
						continue;
					}
					try {
						value[i] = new String(
								childValue.getBytes("ISO-8859-1"),
								this.encoding);
					} catch (Exception e) {
					}
				}
			}
		}
		return result;
	}

}
