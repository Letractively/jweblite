package jweblite.web.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import jweblite.data.MultiValueHashMap;
import jweblite.data.MultiValueMap;
import jweblite.util.StringUtils;

import org.apache.commons.fileupload.FileItem;

public class FormModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private MultiValueMap parameterMap = new MultiValueHashMap();

	/**
	 * Default constructor.
	 */
	public FormModel() {
		super();
	}

	/**
	 * Get Parameter Values
	 * 
	 * @param name
	 *            String
	 * @return String[]
	 */
	public String[] getParameterValues(String name) {
		List<String> paramValueList = (List) parameterMap.get(name);
		return (paramValueList != null ? paramValueList
				.toArray(new String[paramValueList.size()]) : null);
	}

	/**
	 * Get Parameter
	 * 
	 * @param name
	 *            String
	 * @return String
	 */
	public String getParameter(String name) {
		List<String> paramValueList = (List) parameterMap.get(name);
		return (paramValueList != null ? StringUtils.join(paramValueList, ",")
				: null);
	}

	/**
	 * Get Parameter Names
	 * 
	 * @return Enumeration
	 */
	public Enumeration getParameterNames() {
		return Collections.enumeration(parameterMap.keySet());
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
		List tempList = new ArrayList();
		tempList.add(value);
		parameterMap.replaceAll(name, tempList);
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
		parameterMap.put(name, value);
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
		parameterMap.remove(name);
	}

	/**
	 * Clear Parameter Map
	 */
	public void clearParameterMap() {
		parameterMap.clear();
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
		return StringUtils.getStringValue(getParameter(name), nullValue,
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
		return StringUtils.getStringValue(getParameter(name), nullValue);
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
		return StringUtils.getHtmlStringValue(getParameter(name), nullValue,
				isIgnoreEmpty);
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
		return StringUtils.getHtmlStringValue(getParameter(name), nullValue);
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
	public String getHtmlParameter(String name) {
		return StringUtils.getHtmlStringValue(getParameter(name), null);
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
		return StringUtils.getIntValue(getParameter(name), errorValue,
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
		return StringUtils.getIntValue(getParameter(name), errorValue);
	}

	/**
	 * Get Long Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            long
	 * @param nullValue
	 *            long
	 * @return long
	 */
	public long getLongParameter(String name, long errorValue, long nullValue) {
		return StringUtils.getLongValue(getParameter(name), errorValue,
				nullValue);
	}

	/**
	 * Get Long Parameter
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            long
	 * @return long
	 */
	public long getLongParameter(String name, long errorValue) {
		return StringUtils.getLongValue(getParameter(name), errorValue);
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
		return StringUtils.getDoubleValue(getParameter(name), errorValue,
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
		return StringUtils.getDoubleValue(getParameter(name), errorValue);
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
		return StringUtils.getDateValue(getParameter(name), pattern,
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
		return StringUtils
				.getDateValue(getParameter(name), pattern, errorValue);
	}

	/**
	 * Get Parameter Map
	 * 
	 * @return MultiValueMap
	 */
	public MultiValueMap getParameterMap() {
		return parameterMap;
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

}