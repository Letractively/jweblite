package jweblite.util;

import java.util.Collection;

public class LoopIterator<T> {

	private T[] array = null;
	private int arraySize = 0;
	private int index = -1;

	/**
	 * Default constructor.
	 */
	public LoopIterator(T[] array) {
		super();
		this.setArray(array);
	}

	/**
	 * Default constructor.
	 */
	public LoopIterator(Collection<T> c) {
		this(CollectionUtils.toArray(c));
	}

	/**
	 * Next
	 * 
	 * @return T
	 */
	public T next() {
		if (array == null || arraySize <= 0) {
			return null;
		}
		// increase index
		index++;
		if (index < 0 || index >= arraySize) {
			index = 0;
		}
		// get element
		return array[index];
	}

	/**
	 * Reset
	 */
	public void reset() {
		this.index = -1;
	}

	/**
	 * Size
	 * 
	 * @return int
	 */
	public int size() {
		return arraySize;
	}

	/**
	 * Get Array
	 * 
	 * @return T[]
	 */
	public T[] getArray() {
		return array;
	}

	/**
	 * Set Array
	 * 
	 * @param array
	 *            T[]
	 */
	public void setArray(T[] array) {
		this.array = array;
		this.arraySize = (array != null ? array.length : 0);
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

}