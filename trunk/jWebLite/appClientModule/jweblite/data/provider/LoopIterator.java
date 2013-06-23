package jweblite.data.provider;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import jweblite.util.CollectionUtils;

public class LoopIterator<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private int index = -1;

	private Collection<T> list = null;
	private T[] listRefArray = null;
	private int listSize = 0;

	/**
	 * Default constructor.
	 */
	public LoopIterator(Collection<T> c) {
		super();
		setList(c);
	}

	/**
	 * Default constructor.
	 */
	public LoopIterator(T[] array) {
		this((array != null ? Arrays.asList(array) : null));
	}

	/**
	 * Get Previous
	 * 
	 * @return T
	 */
	public T getPrevious() {
		if (listRefArray == null || listSize <= 0) {
			return null;
		}
		// decrease index
		if (index < 0 || index >= listSize) {
			index = listSize - 1;
		} else {
			index = getPreviousIndex();
		}
		// get element
		return listRefArray[index];
	}

	/**
	 * Get Next
	 * 
	 * @return T
	 */
	public T getNext() {
		if (listRefArray == null || listSize <= 0) {
			return null;
		}
		// increase index
		if (index < 0 || index >= listSize) {
			index = 0;
		} else {
			index = getNextIndex();
		}
		// get element
		return listRefArray[index];
	}

	/**
	 * Get Previous Index
	 * 
	 * @return int
	 */
	public int getPreviousIndex() {
		int index = this.index - 1;
		if (index < 0 || index >= listSize) {
			index = (listSize > 0 ? listSize - 1 : 0);
		}
		return index;
	}

	/**
	 * Get Next Index
	 * 
	 * @return int
	 */
	public int getNextIndex() {
		int index = this.index + 1;
		if (index < 0 || index >= listSize) {
			index = 0;
		}
		return index;
	}

	/**
	 * Reset
	 */
	public void reset() {
		index = -1;
	}

	/**
	 * Get Index
	 * 
	 * @return int
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Set Index
	 * 
	 * @param index
	 *            int
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get List
	 * 
	 * @return Collection
	 */
	public Collection<T> getList() {
		return list;
	}

	/**
	 * Get List Size
	 * 
	 * @return int
	 */
	public int getListSize() {
		return listSize;
	}

	/**
	 * Set List
	 * 
	 * @param list
	 *            Collection
	 */
	public void setList(Collection<T> list) {
		this.list = list;

		listRefArray = CollectionUtils.toArray(list);
		listSize = (list != null ? list.size() : 0);
	}

}