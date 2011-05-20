package jweblite.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jweblite.util.callback.EachCallback;

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
		Iterator<T> it = c.iterator();
		int index = 0;
		while (it.hasNext()) {
			T t = it.next();
			if (callback == null) {
				sb.append(t);
			} else {
				sb.append(callback.callback(t, index));
			}
			if (it.hasNext()) {
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

}