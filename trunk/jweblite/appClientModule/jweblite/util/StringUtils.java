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
		StringBuffer result = new StringBuffer();
		int csLength = (cs != null ? cs.length() : 0);
		if (csLength > 0) {
			result.append(Character.toTitleCase(cs.charAt(0)));
			if (csLength > 1) {
				result.append(cs.subSequence(1, csLength));
			}
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
		StringBuffer result = new StringBuffer();
		int lastUrlCommaIndex = -1;
		if (url != null
				&& (lastUrlCommaIndex = url.lastIndexOf(".")) == url
						.indexOf(".")) {
			try {
				String resultClassUri = url.substring(
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
	public static List split(String str, String regex) {
		if (str != null) {
			return Arrays.asList(str.split(regex));
		}
		return new ArrayList();
	}

	/**
	 * Join
	 * 
	 * @param c
	 *            Collection
	 * @return String
	 */
	public static String join(Collection c) {
		StringBuffer sb = new StringBuffer();
		if (c != null) {
			Iterator it = c.iterator();
			while (it.hasNext()) {
				sb.append(it.next());
			}
		}
		return sb.toString();
	}

}