package jweblite.util;

import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CollectionUtils {

	private Log log = LogFactory.getLog(this.getClass());

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
		T[] array = (T[]) Array.newInstance(clazz, c.size());
		return c.toArray(array);
	}

}