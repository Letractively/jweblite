package jweblite.web.wrapper;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import jweblite.util.StringUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteRequestWrapper extends HttpServletRequestWrapper {

	private Log log = LogFactory.getLog(this.getClass());

	private String encoding = null;
	private final boolean isGetMethod;
	private final boolean isMultipart;

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 */
	public JWebLiteRequestWrapper(HttpServletRequest req) {
		super(req);
		this.isGetMethod = ("GET".equalsIgnoreCase(req.getMethod()));
		this.isMultipart = this.isMultipart();
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param encoding
	 *            String
	 * @throws UnsupportedEncodingException
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
	 * Get File Parameter
	 * 
	 * @param name
	 *            String
	 * @return FileItem
	 */
	public FileItem getFileParameter(String name) {
		return null;
	}

	/**
	 * Get Parameter
	 * 
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public String getParameter(String name, String nullValue) {
		return StringUtils.getStringValue(this.getParameter(name), nullValue);
	}

	/**
	 * Get Int Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @param nullValue
	 *            int
	 * @return int
	 */
	public int getIntParameter(String name, int errorValue, int nullValue) {
		return StringUtils.getIntValue(this.getParameter(name), errorValue,
				nullValue);
	}

	/**
	 * Get Int Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public int getIntParameter(String name, int errorValue) {
		return StringUtils.getIntValue(this.getParameter(name), errorValue);
	}

	/**
	 * Get Double Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @param nullValue
	 *            double
	 * @return double
	 */
	public double getDoubleParameter(String name, double errorValue,
			double nullValue) {
		return StringUtils.getDoubleValue(this.getParameter(name), errorValue,
				nullValue);
	}

	/**
	 * Get Double Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @return double
	 */
	public double getDoubleParameter(String name, double errorValue) {
		return StringUtils.getDoubleValue(this.getParameter(name), errorValue);
	}

	/**
	 * Get Date Parameter
	 * 
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @param nullValue
	 *            Date
	 * @return Date
	 */
	public Date getDateParameter(String name, String pattern, Date errorValue,
			Date nullValue) {
		return StringUtils.getDateValue(this.getParameter(name), pattern,
				errorValue, nullValue);
	}

	/**
	 * Get Date Parameter
	 * 
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @return Date
	 */
	public Date getDateParameter(String name, String pattern, Date errorValue) {
		return StringUtils.getDateValue(this.getParameter(name), pattern,
				errorValue);
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
	}

	/**
	 * Is Get Method
	 * 
	 * @return boolean
	 */
	public boolean isGetMethod() {
		return isGetMethod;
	}

	/**
	 * Is Multipart
	 * 
	 * @return boolean
	 */
	public boolean isMultipart() {
		return isMultipart;
	}

}