package jweblite.util;

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

}