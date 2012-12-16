package jweblite.util;

import java.lang.reflect.Array;
import java.util.Collection;

public class CollectionUtils {

	/**
	 * To Array
	 * 
	 * @param c
	 *            Collection
	 * @param clazz
	 *            Class
	 * @return T[]
	 */
	public static <T> T[] toArray(Collection<T> c, Class<?> clazz) {
		if (c == null) {
			return null;
		}
		return c.toArray((T[]) Array.newInstance(clazz, c.size()));
	}

}