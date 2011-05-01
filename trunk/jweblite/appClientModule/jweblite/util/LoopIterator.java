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
	 * Previous
	 * 
	 * @return T
	 */
	public T previous() {
		if (array == null || arraySize <= 0) {
			return null;
		}
		// increase index
		index = this.getPreviousIndex();
		// get element
		return array[index];
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
		// get next index
		index = this.getNextIndex();
		// get element
		return array[index];
	}

	/**
	 * Get Previous Index
	 * 
	 * @return int
	 */
	public int getPreviousIndex() {
		int index = this.index - 1;
		if (index < 0 || index >= arraySize) {
			index = (arraySize > 0 ? arraySize - 1 : 0);
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
		if (index < 0 || index >= arraySize) {
			index = 0;
		}
		return index;
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