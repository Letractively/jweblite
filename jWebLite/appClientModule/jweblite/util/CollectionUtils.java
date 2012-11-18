package jweblite.util;

import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CollectionUtils {

	private static final Log _cat = LogFactory.getLog(CollectionUtils.class);

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