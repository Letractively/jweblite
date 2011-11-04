package jweblite.util.el;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateELUtils {

	private static final Log _cat = LogFactory.getLog(DateELUtils.class);

	/**
	 * ========================================================================
	 * java.util.Date
	 * ========================================================================
	 */

	/**
	 * Current Time Millis
	 * 
	 * @return long
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * Current Date
	 * 
	 * @return Date
	 */
	public static Date currentDate() {
		return new Date();
	}

	/**
	 * Format Date
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Format Date
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            String
	 * @return Date
	 */
	public static Date parseDate(String str, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
		}
		return null;
	}

}