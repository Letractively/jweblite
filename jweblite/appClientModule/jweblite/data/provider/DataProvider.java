package jweblite.data.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DataProvider<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private int perPage = 20;
	private int currentIndex = 0;
	private int viewCountPrefix;
	private int viewCountSuffix;

	private List<T> viewList = null;
	private int totalSize = 0;
	private int totalPageCount = 1;

	/**
	 * Default constructor.
	 */
	public DataProvider(int perPage, int viewCountPrefix, int viewCountSuffix) {
		super();
		if (perPage > 0) {
			this.perPage = perPage;
		}
		this.viewCountPrefix = viewCountPrefix;
		this.viewCountSuffix = viewCountSuffix;
		// init
		this.initialize();
	}

	/**
	 * Default constructor.
	 */
	public DataProvider(int perPage) {
		this(perPage, 4, 5);
	}

	/**
	 * Initialize
	 */
	protected void initialize() {
		this.totalSize = this.loadTotalSize();
		this.totalPageCount = this.totalSize / this.perPage + 1;
	}

	/**
	 * Load View List
	 * 
	 * @param first
	 *            int
	 * @param count
	 *            int
	 */
	public abstract List<T> loadViewList(int first, int count);

	/**
	 * Load Total Size
	 * 
	 * @return int
	 */
	public abstract int loadTotalSize();

	/**
	 * Get PerPage
	 * 
	 * @return int
	 */
	public int getPerPage() {
		return perPage;
	}

	/**
	 * Set PerPage
	 * 
	 * @param perPage
	 *            int
	 */
	public void setPerPage(int perPage) {
		if (perPage <= 0) {
			return;
		}
		this.perPage = perPage;
		// recalculate
		this.currentIndex = 0;
		this.viewList = this.loadViewList(this.currentIndex * perPage, perPage);
		this.totalPageCount = this.totalSize / perPage + 1;
	}

	/**
	 * Get Current Index
	 * 
	 * @return int
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * Set Current Index
	 * 
	 * @param currentIndex
	 *            int
	 */
	public void setCurrentIndex(int currentIndex) {
		if (currentIndex < 0 || currentIndex >= this.totalPageCount) {
			return;
		}
		this.currentIndex = currentIndex;
		// recalculate
		this.viewList = this.loadViewList(currentIndex * this.perPage,
				this.perPage);
	}

	/**
	 * Is Has Previous
	 * 
	 * @return boolean
	 */
	public boolean isHasPrevious() {
		return this.currentIndex > 0;
	}

	/**
	 * Is Has Next
	 * 
	 * @return boolean
	 */
	public boolean isHasNext() {
		return this.currentIndex < this.totalPageCount - 1;
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
		int minIndex = this.currentIndex - this.viewCountPrefix;
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
		int maxIndex = this.currentIndex + this.viewCountSuffix;
		if (maxIndex >= this.totalPageCount) {
			maxIndex = this.totalPageCount - 1;
		}
		return maxIndex;
	}

	/**
	 * Get View Index List
	 * 
	 * @return List
	 */
	public List<Integer> getViewIndexList() {
		List<Integer> viewIndexList = new ArrayList();
		for (int i = this.getMinimumViewIndex(); i <= this
				.getMaximumViewIndex(); i++) {
			viewIndexList.add(i);
		}
		return viewIndexList;
	}

	/**
	 * Get View List
	 * 
	 * @return List
	 */
	public List<T> getViewList() {
		return viewList;
	}

	/**
	 * Get Total Size
	 * 
	 * @return int
	 */
	public int getTotalSize() {
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