package jweblite.util.el;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NumberELUtils {

	private Log log = LogFactory.getLog(this.getClass());

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
			return -1;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return -1;
	}

}