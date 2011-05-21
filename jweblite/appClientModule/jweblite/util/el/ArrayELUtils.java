package jweblite.util.el;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ArrayELUtils {

	private Log log = LogFactory.getLog(this.getClass());

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
	public static String toString(Object[] a) {
		if (a == null) {
			return null;
		}
		return Arrays.toString(a);
	}

}