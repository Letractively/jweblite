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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtils {

	private Log log = LogFactory.getLog(this.getClass());

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
	 * Parse Url Path To Class Name
	 * 
	 * @param url
	 *            String
	 * @param urlPathPadding
	 *            int
	 * @return String
	 */
	public static String parseUrlPathToClassName(String url, int urlPathPadding) {
		// skip invalid pattern
		if (url == null) {
			return null;
		}
		String currentUrl = url
				.substring(indexOf(url, "/", urlPathPadding) + 1);
		int urlLength = -1;
		int lastUrlCommaIndex = -1;
		if ((urlLength = currentUrl.length()) == 0
				|| (lastUrlCommaIndex = currentUrl.lastIndexOf(".")) != currentUrl
						.indexOf(".")) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		try {
			// replace all '/' to '.'
			String resultClassName = currentUrl.substring(0,
					(lastUrlCommaIndex >= 0 ? lastUrlCommaIndex : urlLength))
					.replace("/", ".");
			if (resultClassName.length() > 0) {
				// package name
				int resultClassNamePackageIndex = resultClassName
						.lastIndexOf(".") + 1;
				if (resultClassNamePackageIndex > 0) {
					result.append(resultClassName.substring(0,
							resultClassNamePackageIndex));
				}
				// class name
				result.append(resultClassName
						.substring(resultClassNamePackageIndex));
			}
		} catch (Exception e) {
		}
		return result.toString();
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
		if (c == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int lastIndex = c.size() - 1;
		int index = 0;
		for (T child : c) {
			if (callback == null) {
				sb.append(child);
			} else {
				sb.append(callback.callback(child, index));
			}
			if (index < lastIndex) {
				sb.append(separator);
			}
			index++;
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
	 * @return String
	 */
	public static <T> String join(T[] array, String separator) {
		if (array == null) {
			return "";
		}
		return join(Arrays.asList(array), separator);
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
		return result;
	}

	/**
	 * Encode File Name
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param fileName
	 *            String
	 * @param encoding
	 *            String
	 * @return String
	 */
	public static String encodeFileName(HttpServletRequest req,
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

}