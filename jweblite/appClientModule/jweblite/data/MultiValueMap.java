package jweblite.data;

import java.util.Collection;
import java.util.Map;

public interface MultiValueMap extends Map {

	/**
	 * Put
	 * 
	 * @param key
	 *            Object
	 */
	public void put(Object key);

	/**
	 * Put All
	 * 
	 * @param key
	 *            Object
	 * @param c
	 *            Collection
	 */
	public void putAll(Object key, Collection c);

	/**
	 * Put All
	 * 
	 * @param key
	 *            Object
	 * @param value
	 *            Object
	 */
	public void putAll(Object key, Object value);

	/**
	 * Put All Reversed
	 * 
	 * @param key
	 *            Object
	 * @param c
	 *            Collection
	 * @param isReverseBefore
	 *            boolean
	 */
	public void putAllReversed(Object key, Collection c, boolean isReverseBefore);

	/**
	 * Values Original
	 * 
	 * @return Collection
	 */
	public Collection valuesOriginal();

	/**
	 * Values Reversed
	 * 
	 * @param isDeeply
	 *            boolean
	 * @return Collection
	 */
	public Collection valuesReversed(boolean isDeeply);

}