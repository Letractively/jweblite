package jweblite.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jweblite.util.callback.EachCallback;

import org.apache.commons.codec.net.BCodec;
import org.apache.commons.lang.StringEscapeUtils;

public class StringUtils {

	/**
	 * To Title Case
	 * 
	 * @param cs
	 *            CharSequence
	 * @return String
	 */
	public static String toTitleCase(CharSequence cs) {
		int csLength = -1;
		if (cs == null || (csLength = cs.length()) <= 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		// length > 0
		result.append(Character.toTitleCase(cs.charAt(0)));
		if (csLength > 1) {
			result.append(cs.subSequence(1, csLength));
		}
		return result.toString();
	}

	/**
	 * To New Charset
	 * 
	 * @param str
	 *            String
	 * @param oldCharset
	 *            String
	 * @param newCharset
	 *            String
	 * @return String
	 */
	public static String toNewCharset(String str, String oldCharset,
			String newCharset) {
		if (str == null) {
			return "";
		}
		try {
			if (oldCharset != null && newCharset != null) {
				return new String(str.getBytes(oldCharset), newCharset);
			} else if (oldCharset != null) {
				return new String(str.getBytes(oldCharset));
			} else if (newCharset != null) {
				return new String(str.getBytes(), newCharset);
			}
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * Split
	 * 
	 * @param str
	 *            String
	 * @param regex
	 *            String
	 * @return List
	 */
	public static List<String> split(String str, String regex) {
		if (str == null) {
			new ArrayList();
		}
		return Arrays.asList(str.split(regex));
	}

	/**
	 * Join
	 * 
	 * @param c
	 *            Collection
	 * @param separator
	 *            String
	 * @param callback
	 *            EachCallback
	 * @return String
	 */
	public static <T> String join(Collection<T> c, String separator,
			EachCallback<T> callback) {
		int size = (c != null ? c.size() : 0);
		if (size <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int lastIndex = size - 1;
		int i = 0;
		for (T child : c) {
			sb.append(callback == null ? child : callback.callback(child, i));
			if (i < lastIndex) {
				sb.append(separator);
			}
			i++;
		}
		return sb.toString();
	}

	/**
	 * Join
	 * 
	 * @param c
	 *            Collection
	 * @param separator
	 *            String
	 * @return String
	 */
	public static <T> String join(Collection<T> c, String separator) {
		return join(c, separator, null);
	}

	/**
	 * Join
	 * 
	 * @param array
	 *            T[]
	 * @param separator
	 *            String
	 * @param callback
	 *            EachCallback
	 * @return String
	 */
	public static <T> String join(T[] array, String separator,
			EachCallback<T> callback) {
		if (array == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int arraySize = array.length;
		int lastIndex = arraySize - 1;
		for (int i = 0; i < arraySize; i++) {
			T child = array[i];
			sb.append(callback == null ? child : callback.callback(child, i));
			if (i < lastIndex) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	/**
	 * Join
	 * 
	 * @param array
	 *            T[]
	 * @param separator
	 *            String
	 * @return String
	 */
	public static <T> String join(T[] array, String separator) {
		return join(array, separator, null);
	}

	/**
	 * Index Of
	 * 
	 * @param str
	 *            String
	 * @param searchStr
	 *            String
	 * @param repeat
	 *            int
	 * @return int
	 */
	public static int indexOf(String str, String searchStr, int repeat) {
		if (str == null || searchStr == null || str.length() == 0
				|| searchStr.length() == 0) {
			return -1;
		}
		int result = -1;
		int currentRepeat = (repeat > 0 ? repeat : 0);
		int offset = -1;
		while (currentRepeat >= 0
				&& (offset = str.indexOf(searchStr, offset + 1)) != -1) {
			result = offset;
			currentRepeat--;
		}
		if (currentRepeat >= 0) {
			return -1;
		}
		return result;
	}

	/**
	 * Encode Download File Name
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param fileName
	 *            String
	 * @param encoding
	 *            String
	 * @return String
	 */
	public static String encodeDownloadFileName(HttpServletRequest req,
			String fileName, String encoding) {
		if (req == null || fileName == null || encoding == null) {
			return "";
		}
		String result = "";
		try {
			if (req.getHeader("User-Agent").indexOf("MSIE") != -1) {
				result = URLEncoder.encode(fileName, encoding);
			} else {
				result = new BCodec().encode(fileName, encoding);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * Get String Value
	 * 
	 * @param str
	 *            String
	 * @param nullValue
	 *            String
	 * @param isIgnoreEmpty
	 *            boolean
	 * @return String
	 */
	public static String getStringValue(String str, String nullValue,
			boolean isIgnoreEmpty) {
		if (str == null || (isIgnoreEmpty && str.length() <= 0)) {
			return nullValue;
		}
		return str;
	}

	/**
	 * Get String Value
	 * 
	 * @param str
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getStringValue(String str, String nullValue) {
		return getStringValue(str, nullValue, false);
	}

	/**
	 * Get Html String Value
	 * 
	 * @param str
	 *            String
	 * @param nullValue
	 *            String
	 * @param isIgnoreEmpty
	 *            boolean
	 * @return String
	 */
	public static String getHtmlStringValue(String str, String nullValue,
			boolean isIgnoreEmpty) {
		String result = str;
		if (str == null || (isIgnoreEmpty && str.length() <= 0)) {
			result = nullValue;
		}
		if (result != null) {
			result = StringEscapeUtils.escapeHtml(result);
		}
		return result;
	}

	/**
	 * Get Html String Value
	 * 
	 * @param str
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getHtmlStringValue(String str, String nullValue) {
		return getHtmlStringValue(str, nullValue, false);
	}

	/**
	 * Get Int Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            int
	 * @param nullValue
	 *            int
	 * @return int
	 */
	public static int getIntValue(String str, int errorValue, int nullValue) {
		if (str == null) {
			return nullValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return errorValue;
	}

	/**
	 * Get Int Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public static int getIntValue(String str, int errorValue) {
		return getIntValue(str, errorValue, errorValue);
	}

	/**
	 * Get Long Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            long
	 * @param nullValue
	 *            long
	 * @return long
	 */
	public static long getLongValue(String str, long errorValue, long nullValue) {
		if (str == null) {
			return nullValue;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
		}
		return errorValue;
	}

	/**
	 * Get Long Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            long
	 * @return long
	 */
	public static long getLongValue(String str, long errorValue) {
		return getLongValue(str, errorValue, errorValue);
	}

	/**
	 * Get Double Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            double
	 * @param nullValue
	 *            double
	 * @return double
	 */
	public static double getDoubleValue(String str, double errorValue,
			double nullValue) {
		if (str == null) {
			return nullValue;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		return errorValue;
	}

	/**
	 * Get Double Value
	 * 
	 * @param str
	 *            String
	 * @param errorValue
	 *            double
	 * @return double
	 */
	public static double getDoubleValue(String str, double errorValue) {
		return getDoubleValue(str, errorValue, errorValue);
	}

	/**
	 * Get Date Value
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @param nullValue
	 *            Date
	 * @return Date
	 */
	public static Date getDateValue(String str, String pattern,
			Date errorValue, Date nullValue) {
		if (str == null) {
			return nullValue;
		}
		try {
			return new SimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
		}
		return errorValue;
	}

	/**
	 * Get Date Value
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @return Date
	 */
	public static Date getDateValue(String str, String pattern, Date errorValue) {
		return getDateValue(str, pattern, errorValue, errorValue);
	}

	/**
	 * Format String
	 * 
	 * @param str
	 *            String
	 * @param prefix
	 *            String
	 * @param args
	 *            Object...
	 * @return String
	 */
	public static String format(String str, String prefix, Object... args) {
		if (str == null) {
			return null;
		}
		int argsSize = 0;
		if (prefix == null || args == null || str.length() <= 0
				|| prefix.length() <= 0 || (argsSize = args.length) <= 0) {
			return str;
		}
		String[] searchArray = new String[argsSize];
		String[] replacementArray = new String[argsSize];
		for (int i = 0; i < argsSize; i++) {
			int reverseIndex = argsSize - 1 - i;
			searchArray[i] = prefix.concat(String.valueOf(reverseIndex + 1));
			replacementArray[i] = String.valueOf(args[reverseIndex]);
		}
		return org.apache.commons.lang.StringUtils.replaceEach(str,
				searchArray, replacementArray);
	}

}