package jweblite.util.el;

import java.util.Collections;
import java.util.List;

public class CollectionELUtils {

	/**
	 * ========================================================================
	 * java.util.Collections
	 * ========================================================================
	 */

	/**
	 * Sort
	 * 
	 * @param list
	 *            List{T}
	 * @return List{T}
	 */
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
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
	 *            List{?}
	 * @return List{?}
	 */
	public static List<?> reverse(List<?> list) {
		if (list == null) {
			return null;
		}
		Collections.reverse(list);
		return list;
	}

}