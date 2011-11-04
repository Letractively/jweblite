package jweblite.util.el;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NumberELUtils {

	private static final Log _cat = LogFactory.getLog(NumberELUtils.class);

	/**
	 * ========================================================================
	 * java.lang.Number
	 * ========================================================================
	 */

	/**
	 * Parse Int
	 * 
	 * @param str
	 *            String
	 * @return int
	 */
	public static int parseInt(String str) {
		if (str == null) {
			return 0;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return 0;
	}

}