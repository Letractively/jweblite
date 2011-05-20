package jweblite.util;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoopIterator<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private int index = -1;

	private T[] array = null;
	private int arraySize = 0;

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
	 * Get Previous
	 * 
	 * @return T
	 */
	public T getPrevious() {
		if (this.array == null || this.arraySize <= 0) {
			return null;
		}
		// decrease index
		if (this.index < 0 || this.index >= this.arraySize) {
			this.index = this.arraySize - 1;
		} else {
			this.index = this.getPreviousIndex();
		}
		// get element
		return this.array[this.index];
	}

	/**
	 * Get Next
	 * 
	 * @return T
	 */
	public T getNext() {
		if (this.array == null || this.arraySize <= 0) {
			return null;
		}
		// increase index
		if (this.index < 0 || this.index >= this.arraySize) {
			this.index = 0;
		} else {
			this.index = this.getNextIndex();
		}
		// get element
		return this.array[this.index];
	}

	/**
	 * Get Previous Index
	 * 
	 * @return int
	 */
	public int getPreviousIndex() {
		int index = this.index - 1;
		if (index < 0 || index >= this.arraySize) {
			index = (this.arraySize > 0 ? this.arraySize - 1 : 0);
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
		if (index < 0 || index >= this.arraySize) {
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
	 * Get Array
	 * 
	 * @return T[]
	 */
	public T[] getArray() {
		return array;
	}

	/**
	 * Get Array Size
	 * 
	 * @return int
	 */
	public int getArraySize() {
		return arraySize;
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

}