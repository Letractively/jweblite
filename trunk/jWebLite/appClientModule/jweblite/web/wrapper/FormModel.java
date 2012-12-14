package jweblite.web.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
	 * @return List{T}
	 */
	public <T> List<T> getParameterValues(String name) {
		if (name == null) {
			return null;
		}
		return (List<T>) parameterMap.get(name);
	}

	/**
	 * Set Parameter Values
	 * 
	 * @param name
	 *            String
	 * @param values
	 *            Collection{T}
	 */
	public <T> void setParameterValues(String name, Collection<T> values) {
		if (name == null) {
			return;
		}
		if (values == null) {
			values = new ArrayList<T>();
		} else {
			values = new ArrayList<T>(values);
		}
		parameterMap.replaceAll(name, values);
	}

	/**
	 * Get Parameter Names
	 * 
	 * @return List{String}
	 */
	public List<String> getParameterNames() {
		return new ArrayList<String>(parameterMap.keySet());
	}

	/**
	 * Set Parameter
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            T
	 */
	public <T> void setParameter(String name, T value) {
		if (name == null) {
			return;
		}
		List<T> paramValueList = new ArrayList<T>();
		if (value != null) {
			paramValueList.add(value);
		}
		parameterMap.replaceAll(name, paramValueList);
	}

	/**
	 * Put Parameter
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            T
	 */
	public <T> void putParameter(String name, T value) {
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
	 * Contains Parameter
	 * 
	 * @param name
	 *            String
	 */
	public boolean containsParameter(String name) {
		if (name == null) {
			return false;
		}
		return parameterMap.containsKey(name);
	}

	/**
	 * Clear Parameter Map
	 */
	public void clearaAllParameters() {
		parameterMap.clear();
	}

	/**
	 * Get String
	 * 
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public String getString(String name, String nullValue) {
		List<String> paramValueList = getParameterValues(name);
		String value = (paramValueList != null ? StringUtils.join(
				paramValueList, ",") : null);
		return StringUtils.getStringValue(value, nullValue, false);
	}

	/**
	 * Get String
	 * 
	 * @param name
	 *            String
	 * @return String
	 */
	public String getString(String name) {
		return getString(name, "");
	}

	/**
	 * Get Escaped String
	 * 
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public String getEscapedString(String name, String nullValue) {
		List<String> paramValueList = getParameterValues(name);
		String value = (paramValueList != null ? StringUtils.join(
				paramValueList, ",") : null);
		return StringUtils.getHtmlStringValue(value, nullValue, false);
	}

	/**
	 * Get Escaped String
	 * 
	 * @param name
	 *            String
	 * @return String
	 */
	public String getEscapedString(String name) {
		return getEscapedString(name, "");
	}

	/**
	 * Get Int
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @param nullValue
	 *            int
	 * @return int
	 */
	public int getInt(String name, int errorValue, int nullValue) {
		return StringUtils.getIntValue(getString(name, null), errorValue,
				nullValue);
	}

	/**
	 * Get Int
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public int getInt(String name, int errorValue) {
		return getInt(name, errorValue, errorValue);
	}

	/**
	 * Get Long
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            long
	 * @param nullValue
	 *            long
	 * @return long
	 */
	public long getLong(String name, long errorValue, long nullValue) {
		return StringUtils.getLongValue(getString(name, null), errorValue,
				nullValue);
	}

	/**
	 * Get Long
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            long
	 * @return long
	 */
	public long getLong(String name, long errorValue) {
		return getLong(name, errorValue, errorValue);
	}

	/**
	 * Get Double
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @param nullValue
	 *            double
	 * @return double
	 */
	public double getDouble(String name, double errorValue, double nullValue) {
		return StringUtils.getDoubleValue(getString(name, null), errorValue,
				nullValue);
	}

	/**
	 * Get Double
	 * 
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @return double
	 */
	public double getDouble(String name, double errorValue) {
		return getDouble(name, errorValue, errorValue);
	}

	/**
	 * Get Date
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
	public Date getDate(String name, String pattern, Date errorValue,
			Date nullValue) {
		return StringUtils.getDateValue(getString(name, null), pattern,
				errorValue, nullValue);
	}

	/**
	 * Get Date
	 * 
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @return Date
	 */
	public Date getDate(String name, String pattern, Date errorValue) {
		return getDate(name, pattern, errorValue, errorValue);
	}

	/**
	 * Get File
	 * 
	 * @param name
	 *            String
	 * @return FileItem
	 */
	public FileItem getFile(String name) {
		List<FileItem> paramValueList = getParameterValues(name);
		int paramValueSize = (paramValueList != null ? paramValueList.size()
				: 0);
		return (paramValueSize > 0 ? paramValueList.get(paramValueSize - 1)
				: null);
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