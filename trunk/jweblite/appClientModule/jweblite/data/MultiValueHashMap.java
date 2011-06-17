package jweblite.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiValueHashMap extends HashMap implements MultiValueMap {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Default constructor.
	 */
	public MultiValueHashMap() {
		super();
	}

	/**
	 * Default constructor.
	 */
	public MultiValueHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueHashMap(Map m) {
		super(m);
	}

	@Override
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

	@Override
	public void putAll(Object key, Collection c) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		valueList.addAll(c);
	}

	@Override
	public void putAllReversed(Object key, Collection list,
			boolean isReverseBefore) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		if (isReverseBefore) {
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

	@Override
	public Collection valuesOriginal() {
		return super.values();
	}

	@Override
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