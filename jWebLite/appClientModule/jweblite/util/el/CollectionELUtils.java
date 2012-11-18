package jweblite.util.el;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CollectionELUtils {

	private static final Log _cat = LogFactory.getLog(CollectionELUtils.class);

	/**
	 * ========================================================================
	 * java.util.Collections
	 * ========================================================================
	 */

	/**
	 * Sort
	 * 
	 * @param list
	 *            List
	 * @return List
	 */
	public static List sort(List list) {
		if (list == null) {
			return null;
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * Reverse
	 * 
	 * @param list
	 *            List
	 * @return List
	 */
	public static List reverse(List list) {
		if (list == null) {
			return null;
		}
		Collections.reverse(list);
		return list;
	}

}