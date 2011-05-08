package jweblite.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jweblite.util.CollectionUtils;

public class CollectionDataProvider<T> extends DataProvider<T> {

	private static final long serialVersionUID = 1L;

	private T[] allArray = null;
	private int allArraySize = 0;

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage) {
		super(perPage);
		this.setAllArray(array);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage) {
		this(CollectionUtils.toArray(c), perPage);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage, int viewCountPrefix,
			int viewCountSuffix) {
		super(perPage, viewCountPrefix, viewCountSuffix);
		this.setAllArray(array);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage,
			int viewCountPrefix, int viewCountSuffix) {
		this(CollectionUtils.toArray(c), perPage, viewCountPrefix,
				viewCountSuffix);
	}

	@Override
	public List<T> loadViewList(int first, int count) {
		if (this.allArray == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (int i = first; i < first + count && i < this.allArraySize; i++) {
			list.add(this.allArray[i]);
		}
		return list;
	}

	@Override
	public int loadTotalSize() {
		return allArraySize;
	}

	/**
	 * Get All Array
	 * 
	 * @return T[]
	 */
	public T[] getAllArray() {
		return allArray;
	}

	/**
	 * Set All Array
	 * 
	 * @param allArray
	 *            T[]
	 */
	public void setAllArray(T[] allArray) {
		this.allArray = allArray;
		this.allArraySize = (this.allArray != null ? allArray.length : 0);
		this.initialize();
		this.setCurrentIndex(0);
	}

	/**
	 * Set All Array
	 * 
	 * @param c
	 *            Collection<T>
	 */
	public void setAllArray(Collection<T> c) {
		this.setAllArray(CollectionUtils.toArray(c));
	}

	/**
	 * Get All Array Size
	 * 
	 * @return int
	 */
	public int getAllArraySize() {
		return allArraySize;
	}

}