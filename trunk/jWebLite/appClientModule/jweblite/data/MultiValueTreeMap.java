package jweblite.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MultiValueTreeMap extends TreeMap implements MultiValueMap {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MultiValueTreeMap() {
		super();
	}

	/**
	 * Default constructor.
	 */
	public MultiValueTreeMap(Comparator comparator) {
		super(comparator);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueTreeMap(Map m) {
		super();
		putAll(m);
	}

	/**
	 * Default constructor.
	 */
	public MultiValueTreeMap(SortedMap m) {
		super();
		putAll(m);
	}

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

	public void putAll(Object key, Collection c) {
		List valueList = (List) super.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			super.put(key, valueList);
		}
		valueList.addAll(c);
	}

	@Override
	public void putAll(Map m) {
		if (m == null) {
			return;
		}
		Iterator mapIt = m.keySet().iterator();
		while (mapIt.hasNext()) {
			Object key = mapIt.next();
			put(key, m.get(key));
		}
	}

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

	public void replaceAll(Object key, Collection c) {
		super.put(key, c);
	}

	@Override
	public Collection values() {
		List allValueList = new ArrayList();

		Iterator valueIt = super.values().iterator();
		while (valueIt.hasNext()) {
			List valueList = (List) valueIt.next();
			allValueList.addAll(valueList);
		}
		return allValueList;
	}

	public Collection valuesOriginal() {
		return super.values();
	}

	public Collection valuesReversed(boolean isDeeply) {
		List allValueList = new ArrayList();

		Iterator valueIt = super.values().iterator();
		while (valueIt.hasNext()) {
			List valueItem = (List) valueIt.next();
			if (isDeeply) {
				Collections.reverse(valueItem);
			}
			allValueList.addAll(0, valueItem);
		}
		return allValueList;
	}

}