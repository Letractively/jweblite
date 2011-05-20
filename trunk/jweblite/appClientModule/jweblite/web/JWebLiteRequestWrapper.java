package jweblite.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteRequestWrapper extends HttpServletRequestWrapper {

	private Log log = LogFactory.getLog(this.getClass());

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

	/**
	 * Get Parameter
	 * 
	 * @param name
	 *            String
	 * @param oldValue
	 *            String
	 * @return String
	 */
	public String getParameter(String name, String oldValue) {
		String result = this.getParameter(name);
		if (result == null) {
			return oldValue;
		}
		return result;
	}

	/**
	 * Get Int Parameter
	 * 
	 * @param name
	 *            String
	 * @param defaultValue
	 *            int
	 * @param oldValue
	 *            int
	 * @return int
	 */
	public int getIntParameter(String name, int defaultValue, int oldValue) {
		String result = this.getParameter(name);
		if (result == null) {
			return oldValue;
		}
		try {
			return Integer.parseInt(result);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * Get Int Parameter
	 * 
	 * @param name
	 *            String
	 * @param defaultValue
	 *            int
	 * @return int
	 */
	public int getIntParameter(String name, int defaultValue) {
		return this.getIntParameter(name, defaultValue, defaultValue);
	}

	/**
	 * Get Double Parameter
	 * 
	 * @param name
	 *            String
	 * @param defaultValue
	 *            double
	 * @param oldValue
	 *            double
	 * @return double
	 */
	public double getDoubleParameter(String name, double defaultValue,
			double oldValue) {
		String result = this.getParameter(name);
		if (result == null) {
			return oldValue;
		}
		try {
			return Double.parseDouble(result);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * Get Double Parameter
	 * 
	 * @param name
	 *            String
	 * @param defaultValue
	 *            double
	 * @return double
	 */
	public double getDoubleParameter(String name, double defaultValue) {
		return this.getDoubleParameter(name, defaultValue, defaultValue);
	}

	/**
	 * Get Date Parameter
	 * 
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param defaultValue
	 *            Date
	 * @param oldValue
	 *            Date
	 * @return Date
	 */
	public Date getDateParameter(String name, String pattern,
			Date defaultValue, Date oldValue) {
		String result = this.getParameter(name);
		if (result == null) {
			return oldValue;
		}
		try {
			return new SimpleDateFormat(pattern).parse(result);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * Get Date Parameter
	 * 
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param defaultValue
	 *            Date
	 * @return Date
	 */
	public Date getDateParameter(String name, String pattern, Date defaultValue) {
		return this.getDateParameter(name, pattern, defaultValue, defaultValue);
	}

}