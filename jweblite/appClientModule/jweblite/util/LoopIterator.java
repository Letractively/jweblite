package jweblite.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoopIterator<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private int index = -1;

	private Collection<T> list = null;
	private Object[] listRefArray = null;
	private int listSize = 0;

	/**
	 * Default constructor.
	 */
	public LoopIterator(Collection<T> c) {
		super();
		this.setList(c);
	}

	/**
	 * Default constructor.
	 */
	public LoopIterator(T[] array) {
		this(Arrays.asList(array));
	}

	/**
	 * Get Previous
	 * 
	 * @return T
	 */
	public T getPrevious() {
		if (this.listRefArray == null || this.listSize <= 0) {
			return null;
		}
		// decrease index
		if (this.index < 0 || this.index >= this.listSize) {
			this.index = this.listSize - 1;
		} else {
			this.index = this.getPreviousIndex();
		}
		// get element
		return (T) this.listRefArray[this.index];
	}

	/**
	 * Get Next
	 * 
	 * @return T
	 */
	public T getNext() {
		if (this.listRefArray == null || this.listSize <= 0) {
			return null;
		}
		// increase index
		if (this.index < 0 || this.index >= this.listSize) {
			this.index = 0;
		} else {
			this.index = this.getNextIndex();
		}
		// get element
		return (T) this.listRefArray[this.index];
	}

	/**
	 * Get Previous Index
	 * 
	 * @return int
	 */
	public int getPreviousIndex() {
		int index = this.index - 1;
		if (index < 0 || index >= this.listSize) {
			index = (this.listSize > 0 ? this.listSize - 1 : 0);
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
		if (index < 0 || index >= this.listSize) {
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
		this.listRefArray = (list != null ? list.toArray() : null);
		this.listSize = (list != null ? list.size() : 0);
	}

}