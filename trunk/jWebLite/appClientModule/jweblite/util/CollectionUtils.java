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
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> c) {
		if (c == null) {
			return null;
		}
		return (T[]) c.toArray(new Object[c.size()]);
	}

}