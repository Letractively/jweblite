package jweblite.util;

import java.util.Collection;

public class CollectionUtils {

	/**
	 * To Array
	 * 
	 * @param c
	 *            Collection
	 * @return T[]
	 */
	public static <T> T[] toArray(Collection<T> c) {
		if (c == null) {
			return null;
		}
		T[] array = (T[]) new Object[c.size()];
		int index = 0;
		for (T child : c) {
			array[index] = child;
			index++;
		}
		return array;
	}

}