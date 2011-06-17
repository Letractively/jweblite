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
	 * Put All Reversed
	 * 
	 * @param key
	 *            Object
	 * @param c
	 *            Collection
	 * @param isReverseBefore
	 *            boolean
	 * @return Object
	 */
	public void putAllReversed(Object key, Collection list,
			boolean isReverseBefore);

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