package jweblite.util;

import java.io.Serializable;
import java.util.List;

public abstract class DataProvider<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int perpage = 20;
	private int index = 0;
	private int viewCountPrefix = 4;
	private int viewCountSuffix = 5;

	private List<T> list = null;
	private int totalSize = 0;
	private int totalPageCount = 1;

	/**
	 * Default constructor.
	 */
	public DataProvider(int perpage, int index) {
		super();
		this.index = index;
		this.perpage = perpage;
		// init
		this.initialize();
	}

	/**
	 * Default constructor.
	 */
	public DataProvider(int perpage, int index, int viewCountPrefix,
			int viewCountSuffix) {
		this(perpage, index);
		this.viewCountPrefix = viewCountPrefix;
		this.viewCountSuffix = viewCountSuffix;
		// init
		this.initialize();
	}

	/**
	 * Initialize
	 */
	private void initialize() {
		this.list = this.loadList(this.index * this.perpage, this.perpage);
		this.totalSize = this.loadSize();
		this.totalPageCount = this.totalSize / this.perpage + 1;
	}

	/**
	 * Load List
	 * 
	 * @param first
	 *            int
	 * @param count
	 *            int
	 */
	public abstract List<T> loadList(int first, int count);

	/**
	 * Load Size
	 * 
	 * @return int
	 */
	public abstract int loadSize();

	/**
	 * Get Perpage
	 * 
	 * @return int
	 */
	public int getPerpage() {
		return perpage;
	}

	/**
	 * Set Perpage
	 * 
	 * @param perpage
	 *            int
	 */
	public void setPerpage(int perpage) {
		this.perpage = perpage;
		// recalculate
		this.list = this.loadList(this.index * perpage + 1, this.perpage);
		this.totalPageCount = this.totalSize / this.perpage + 1;
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
		if (this.index < 0 || this.index >= this.totalPageCount) {
			return;
		}
		this.index = index;
		// recalculate
		this.list = this.loadList(this.index * this.perpage + 1, this.perpage);
	}

	/**
	 * Is Has Previous
	 * 
	 * @return int
	 */
	public boolean isHasPrevious() {
		return this.index > 0;
	}

	/**
	 * Is Has Next
	 * 
	 * @return int
	 */
	public boolean isHasNext() {
		return this.index < this.totalPageCount - 1;
	}

	/**
	 * Get View Count Prefix
	 * 
	 * @return int
	 */
	public int getViewCountPrefix() {
		return viewCountPrefix;
	}

	/**
	 * Set View Count Prefix
	 * 
	 * @param viewCountPrefix
	 *            int
	 */
	public void setViewCountPrefix(int viewCountPrefix) {
		this.viewCountPrefix = viewCountPrefix;
	}

	/**
	 * Get View Count Suffix
	 * 
	 * @return int
	 */
	public int getViewCountSuffix() {
		return viewCountSuffix;
	}

	/**
	 * Set View Count Suffix
	 * 
	 * @param viewCountSuffix
	 *            int
	 */
	public void setViewCountSuffix(int viewCountSuffix) {
		this.viewCountSuffix = viewCountSuffix;
	}

	/**
	 * Get Minimum View Index
	 * 
	 * @return int
	 */
	public int getMinimumViewIndex() {
		int minIndex = this.index - this.viewCountPrefix;
		if (minIndex < 0) {
			minIndex = 0;
		}
		return minIndex;
	}

	/**
	 * Get Maximum View Index
	 * 
	 * @return int
	 */
	public int getMaximumViewIndex() {
		int maxIndex = this.index + this.viewCountSuffix;
		if (maxIndex >= this.totalPageCount) {
			maxIndex = this.totalPageCount - 1;
		}
		return maxIndex;
	}

	/**
	 * Get List
	 * 
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Get Size
	 * 
	 * @return int
	 */
	public int getSize() {
		return totalSize;
	}

	/**
	 * Get Total Page Count
	 * 
	 * @return int
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

}