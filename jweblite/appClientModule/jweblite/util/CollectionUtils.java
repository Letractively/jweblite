package jweblite.util;

import java.lang.reflect.Array;
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
		int cSize = c.size();
		if (cSize == 0) {
			return (T[]) new Object[cSize];
		}
		Class<?> clazz = null;
		for (T child : c) {
			clazz = child.getClass();
			break;
		}
		T[] array = (T[]) Array.newInstance(clazz, cSize);
		int index = 0;
		for (T child : c) {
			array[index] = child;
			index++;
		}
		return array;
	}

}