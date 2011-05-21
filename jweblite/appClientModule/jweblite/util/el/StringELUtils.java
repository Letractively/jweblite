package jweblite.util.el;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringELUtils {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * ========================================================================
	 * java.lang.String
	 * ========================================================================
	 */

	/**
	 * Char At
	 * 
	 * @param str
	 *            String
	 * @param index
	 *            int
	 * @return Character
	 */
	public static Character charAt(String str, int index) {
		if (str == null) {
			return null;
		}
		return str.charAt(index);
	}

	/**
	 * Compare To
	 * 
	 * @param str
	 *            String
	 * @param anotherString
	 *            String
	 * @return int
	 */
	public static int compareTo(String str, String anotherString) {
		if (str == null) {
			return 0;
		}
		return str.compareTo(anotherString);
	}

	/**
	 * Compare To Ignore Case
	 * 
	 * @param str
	 *            String
	 * @param anotherString
	 *            String
	 * @return int
	 */
	public static int compareToIgnoreCase(String str, String anotherString) {
		if (str == null) {
			return 0;
		}
		return str.compareToIgnoreCase(anotherString);
	}

	/**
	 * Concat
	 * 
	 * @param str
	 *            String
	 * @param appendStr
	 *            String
	 * @return String
	 */
	public static String concat(String str, String appendStr) {
		if (str == null) {
			return null;
		}
		return str.concat(appendStr);
	}

	/**
	 * Contains
	 * 
	 * @param str
	 *            String
	 * @param s
	 *            CharSequence
	 * @return boolean
	 */
	public static boolean contains(String str, CharSequence s) {
		if (str == null) {
			return false;
		}
		return str.contains(s);
	}

	/**
	 * Ends With
	 * 
	 * @param str
	 *            String
	 * @param suffix
	 *            String
	 * @return boolean
	 */
	public static boolean endsWith(String str, String suffix) {
		if (str == null) {
			return false;
		}
		return str.endsWith(suffix);
	}

	/**
	 * Equals
	 * 
	 * @param str
	 *            String
	 * @param anObject
	 *            Object
	 * @return boolean
	 */
	public static boolean equals(String str, Object anObject) {
		if (str == null) {
			return false;
		}
		return str.equals(anObject);
	}

	/**
	 * Equals IgnoreCase
	 * 
	 * @param str
	 *            String
	 * @param anotherString
	 *            String
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(String str, String anotherString) {
		if (str == null) {
			return false;
		}
		return str.equalsIgnoreCase(anotherString);
	}

	/**
	 * Index Of
	 * 
	 * @param str
	 *            String
	 * @param anObject
	 *            Object
	 * @return int
	 */
	public static int indexOf(String str, Object anObject) {
		if (str == null) {
			return -1;
		}
		if (anObject instanceof Integer) {
			return str.indexOf((Integer) anObject);
		} else if (anObject instanceof Character) {
			return str.indexOf((Character) anObject);
		} else if (anObject instanceof String) {
			return str.indexOf((String) anObject);
		}
		return -1;
	}

	/**
	 * Last Index Of
	 * 
	 * @param str
	 *            String
	 * @param anObject
	 *            Object
	 * @return int
	 */
	public static int lastIndexOf(String str, Object anObject) {
		if (str == null) {
			return -1;
		}
		if (anObject instanceof Integer) {
			return str.lastIndexOf((Integer) anObject);
		} else if (anObject instanceof Character) {
			return str.lastIndexOf((Character) anObject);
		} else if (anObject instanceof String) {
			return str.lastIndexOf((String) anObject);
		}
		return -1;
	}

	/**
	 * Length
	 * 
	 * @param str
	 *            String
	 * @return int
	 */
	public static int length(String str) {
		if (str == null) {
			return 0;
		}
		return str.length();
	}

	/**
	 * Matches
	 * 
	 * @param str
	 *            String
	 * @param regex
	 *            String
	 * @return boolean
	 */
	public static boolean matches(String str, String regex) {
		if (str == null) {
			return false;
		}
		return str.matches(regex);
	}

	/**
	 * Replace
	 * 
	 * @param str
	 *            String
	 * @param target
	 *            Object
	 * @param replacement
	 *            Object
	 * @return String
	 */
	public static String replace(String str, Object target, Object replacement) {
		if (str == null) {
			return null;
		}
		if (target instanceof Character) {
			str.replace((Character) target, (Character) replacement);
		} else if (target instanceof CharSequence) {
			str.replace((CharSequence) target, (CharSequence) replacement);
		}
		return str;
	}

	/**
	 * Replace All
	 * 
	 * @param str
	 *            String
	 * @param regex
	 *            String
	 * @param replacement
	 *            String
	 * @return String
	 */
	public static String replaceAll(String str, String regex, String replacement) {
		if (str == null) {
			return null;
		}
		return str.replaceAll(regex, replacement);
	}

	/**
	 * Replace First
	 * 
	 * @param str
	 *            String
	 * @param regex
	 *            String
	 * @param replacement
	 *            String
	 * @return String
	 */
	public static String replaceFirst(String str, String regex,
			String replacement) {
		if (str == null) {
			return null;
		}
		return str.replaceFirst(regex, replacement);
	}

	/**
	 * Split
	 * 
	 * @param str
	 *            String
	 * @param regex
	 *            String
	 * @param limit
	 *            int
	 * @return String[]
	 */
	public static String[] split(String str, String regex, int limit) {
		if (str == null) {
			return null;
		}
		if (limit < 0) {
			return str.split(regex);
		}
		return str.split(regex, limit);
	}

	/**
	 * Starts With
	 * 
	 * @param str
	 *            String
	 * @param prefix
	 *            String
	 * @return boolean
	 */
	public static boolean startsWith(String str, String prefix) {
		if (str == null) {
			return false;
		}
		return str.startsWith(prefix);
	}

	/**
	 * Substring
	 * 
	 * @param str
	 *            String
	 * @param beginIndex
	 *            int
	 * @param endIndex
	 *            int
	 * @return String
	 */
	public static String substring(String str, int beginIndex, int endIndex) {
		if (str == null) {
			return null;
		}
		if (endIndex < 0) {
			return str.substring(beginIndex);
		}
		return str.substring(beginIndex, endIndex);
	}

	/**
	 * To CharArray
	 * 
	 * @param str
	 *            String
	 * @return char[]
	 */
	public static char[] toCharArray(String str) {
		if (str == null) {
			return null;
		}
		return str.toCharArray();
	}

	/**
	 * To Lower Case
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * Trim
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String trim(String str) {
		if (str == null) {
			return null;
		}
		return str.trim();
	}

	/**
	 * Format
	 * 
	 * @param format
	 *            String
	 * @param args
	 *            Object...
	 * @return String
	 */
	public static String format(String format, Object... args) {
		if (format == null) {
			return null;
		}
		return String.format(format, args);
	}

	public static String format1(String format, Object args1) {
		return format(format, args1);
	}

	public static String format2(String format, Object args1, Object args2) {
		return format(format, args1, args2);
	}

	public static String format3(String format, Object args1, Object args2,
			Object args3) {
		return format(format, args1, args2, args3);
	}

	public static String format4(String format, Object args1, Object args2,
			Object args3, Object args4) {
		return format(format, args1, args2, args3, args4);
	}

	public static String format5(String format, Object args1, Object args2,
			Object args3, Object args4, Object args5) {
		return format(format, args1, args2, args3, args4, args5);
	}

	/**
	 * Value Of
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String valueOf(Object obj) {
		if (obj == null) {
			return null;
		}
		return String.valueOf(obj);
	}

	/**
	 * ========================================================================
	 * org.apache.commons.lang.StringEscapeUtils
	 * ========================================================================
	 */

	/**
	 * Escape Html
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String escapeHtml(String str) {
		if (str == null) {
			return null;
		}
		return StringEscapeUtils.escapeHtml(str);
	}

	/**
	 * Escape JavaScript
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String escapeJavaScript(String str) {
		if (str == null) {
			return null;
		}
		return StringEscapeUtils.escapeJavaScript(str);
	}

	/**
	 * Escape Xml
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String escapeXml(String str) {
		if (str == null) {
			return null;
		}
		return StringEscapeUtils.escapeXml(str);
	}

}