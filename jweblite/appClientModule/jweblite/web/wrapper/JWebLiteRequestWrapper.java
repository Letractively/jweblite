package jweblite.web.wrapper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import jweblite.data.MultiValueHashMap;
import jweblite.data.MultiValueMap;
import jweblite.util.StringUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteRequestWrapper extends HttpServletRequestWrapper {

	private Log log = LogFactory.getLog(this.getClass());

	private String encoding;
	private final boolean isGetMethod;
	private final boolean isMultipart;

	private MultiValueMap parameterMap = new MultiValueHashMap();

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
		super(req);
		this.setEncoding(encoding);
		this.isGetMethod = ("GET".equalsIgnoreCase(req.getMethod()));
		this.isMultipart = this.isMultipart();
		// init
		this.initialize(req);
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @throws UnsupportedEncodingException
	 */
	public JWebLiteRequestWrapper(HttpServletRequest req)
			throws UnsupportedEncodingException {
		this(req, null);
	}

	/**
	 * Initialize
	 * 
	 * @param req
	 *            HttpServletRequest
	 */
	protected void initialize(HttpServletRequest req) {
		for (Enumeration<String> e = req.getParameterNames(); e
				.hasMoreElements();) {
			String paramName = e.nextElement();
			if (paramName == null) {
				continue;
			}
			String[] paramValueArray = super.getParameterValues(paramName);
			if (paramValueArray != null) {
				List<String> paramValueList = new ArrayList();
				for (String paramValue : paramValueArray) {
					if (paramValue == null) {
						continue;
					}
					paramValueList.add(StringUtils.toNewCharset(paramValue,
							"ISO-8859-1", this.encoding));
				}
				this.parameterMap.putAll(paramName, paramValueList);
			}
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		List<String> paramValueList = (List) this.parameterMap.get(name);
		return (paramValueList != null ? paramValueList
				.toArray(new String[paramValueList.size()]) : null);
	}

	@Override
	public String getParameter(String name) {
		List<String> paramValueList = (List) this.parameterMap.get(name);
		return (paramValueList != null ? StringUtils.join(paramValueList, ",")
				: null);
	}

	@Override
	public Map getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration getParameterNames() {
		return Collections.enumeration(this.parameterMap.keySet());
	}

	/**
	 * Set Parameter Map
	 * 
	 * @param parameterMap
	 *            MultiValueMap
	 */
	public void setParameterMap(MultiValueMap parameterMap) {
		this.parameterMap = parameterMap;
	}

	/**
	 * Set Parameter
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            String
	 */
	public void setParameter(String name, String value) {
		if (name == null) {
			return;
		}
		this.parameterMap.putAll(name, value);
	}

	/**
	 * Put Parameter
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            String
	 */
	public void putParameter(String name, String value) {
		if (name == null) {
			return;
		}
		this.parameterMap.put(name, value);
	}

	/**
	 * Remove Parameter
	 * 
	 * @param name
	 *            String
	 */
	public void removeParameter(String name) {
		if (name == null) {
			return;
		}
		this.parameterMap.remove(name);
	}

	/**
	 * Clear Parameter Map
	 */
	public void clearParameterMap() {
		this.parameterMap.clear();
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
	 * @param isIgnoreEmpty
	 *            boolean
	 * @return String
	 */
	public String getParameter(String name, String nullValue,
			boolean isIgnoreEmpty) {
		return StringUtils.getStringValue(this.getParameter(name), nullValue,
				isIgnoreEmpty);
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
	 * Get Html Parameter
	 * 
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @param isIgnoreEmpty
	 *            boolean
	 * @return String
	 */
	public String getHtmlParameter(String name, String nullValue,
			boolean isIgnoreEmpty) {
		return StringUtils.getHtmlStringValue(this.getParameter(name),
				nullValue, isIgnoreEmpty);
	}

	/**
	 * Get Html Parameter
	 * 
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public String getHtmlParameter(String name, String nullValue) {
		return StringUtils.getHtmlStringValue(this.getParameter(name),
				nullValue);
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
	 * @throws UnsupportedEncodingException
	 */
	public void setEncoding(String encoding)
			throws UnsupportedEncodingException {
		this.encoding = encoding;
		if (encoding != null) {
			this.setCharacterEncoding(encoding);
		}
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