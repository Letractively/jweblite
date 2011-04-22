package jweblite.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
	 * @return String
	 */
	public static String parseUrlPathToClassName(String url) {
		int lastUrlCommaIndex = -1;
		if (url == null
				|| (lastUrlCommaIndex = url.lastIndexOf(".")) != url
						.indexOf(".")) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		try {
			String resultClassUri = url
					.substring(
							(url.startsWith("/") ? 1 : 0),
							(lastUrlCommaIndex >= 0 ? lastUrlCommaIndex : url
									.length())).replace("/", ".");
			if (resultClassUri.length() > 0) {
				int resultClassNameIndex = resultClassUri.lastIndexOf(".") + 1;
				if (resultClassNameIndex > 0) {
					result.append(resultClassUri.substring(0,
							resultClassNameIndex));
				}
				// to title case
				result.append(StringUtils.toTitleCase(resultClassUri
						.substring(resultClassNameIndex)));
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
	 * @return String
	 */
	public static String join(Collection c, String separator) {
		if (c == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

}