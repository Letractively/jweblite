package jweblite.util.el;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ArrayELUtils {

	private static final Log _cat = LogFactory.getLog(ArrayELUtils.class);

	/**
	 * ========================================================================
	 * java.util.Arrays
	 * ========================================================================
	 */

	/**
	 * To String
	 * 
	 * @param a
	 *            Object[]
	 * @return String
	 */
	public static List asList(Object[] a) {
		if (a == null) {
			return null;
		}
		return Arrays.asList(a);
	}

	/**
	 * To String
	 * 
	 * @param a
	 *            Object[]
	 * @return String
	 */
	public static String toString(Object[] a) {
		if (a == null) {
			return null;
		}
		return Arrays.toString(a);
	}

}