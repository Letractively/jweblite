package jweblite.util.el;

public class NumberELUtils {

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