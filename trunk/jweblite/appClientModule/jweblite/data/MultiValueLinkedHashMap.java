package jweblite.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiValueLinkedHashMap extends LinkedHashMap {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Default constructor.
	 */
	public MultiValueLinkedHashMap() {
		super();
	}

	/**
	 * Default constructor.
	 */
	public MultiValueLinkedHashMap(int initialCapacity, float loadFactor,
			boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueLinkedHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueLinkedHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueLinkedHashMap(Map m) {
		super(m);
	}

	/**
	 * Put
	 * 
	 * @param key
	 *            Object
	 */
	public void put(Object key) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
	}

	@Override
	public Object put(Object key, Object value) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		return valueList.add(value) ? value : null;
	}

	/**
	 * Put All
	 * 
	 * @param key
	 *            Object
	 * @param c
	 *            Collection
	 */
	public void putAll(Object key, Collection c) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		valueList.addAll(c);
	}

	/**
	 * Put All Reversed
	 * 
	 * @param key
	 *            Object
	 * @param c
	 *            Collection
	 * @param isReverseFirst
	 *            boolean
	 * @return Object
	 */
	public void putAllReversed(Object key, Collection list,
			boolean isReverseFirst) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		if (isReverseFirst) {
			int valueListSize = valueList.size();
			for (Object value : list) {
				valueList.add(valueListSize, value);
			}
		} else {
			valueList.addAll(list);
			Collections.reverse(valueList);
		}
	}

	@Override
	public Collection values() {
		List allValueList = new ArrayList();

		Iterator valueIterator = super.values().iterator();
		while (valueIterator.hasNext()) {
			List valueList = (List) valueIterator.next();
			allValueList.addAll(valueList);
		}
		return allValueList;
	}

	/**
	 * Values Original
	 * 
	 * @return Collection
	 */
	public Collection valuesOriginal() {
		return super.values();
	}

	/**
	 * Values Reversed
	 * 
	 * @param isDeeply
	 *            boolean
	 * @return Collection
	 */
	public Collection valuesReversed(boolean isDeeply) {
		List allValueList = new ArrayList();

		Iterator valueIterator = super.values().iterator();
		while (valueIterator.hasNext()) {
			List valueItem = (List) valueIterator.next();
			if (isDeeply) {
				Collections.reverse(valueItem);
			}
			allValueList.addAll(0, valueItem);
		}
		return allValueList;
	}

}