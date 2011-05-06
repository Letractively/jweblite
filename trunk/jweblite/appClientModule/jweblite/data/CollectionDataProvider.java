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
	public CollectionDataProvider(T[] array, int perPage, int currentIndex) {
		super(perPage, currentIndex);
		this.setAllArray(array);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage, int currentIndex) {
		this(CollectionUtils.toArray(c), perPage, currentIndex);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage, int currentIndex,
			int viewCountPrefix, int viewCountSuffix) {
		super(perPage, currentIndex, viewCountPrefix, viewCountSuffix);
		this.setAllArray(array);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage,
			int currentIndex, int viewCountPrefix, int viewCountSuffix) {
		this(CollectionUtils.toArray(c), perPage, currentIndex,
				viewCountPrefix, viewCountSuffix);
	}

	@Override
	public List<T> loadViewList(int first, int count) {
		if (this.allArray == null) {
			return new ArrayList<T>();
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