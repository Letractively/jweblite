package jweblite.data.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionDataProvider<T> extends DataProvider<T> {
	private static final long serialVersionUID = 1L;

	private Collection<T> list = null;

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage,
			int viewCountPrefix, int viewCountSuffix) {
		super(perPage, viewCountPrefix, viewCountSuffix);
		setList(c);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage, int viewCountPrefix,
			int viewCountSuffix) {
		this((array != null ? Arrays.asList(array) : null), perPage,
				viewCountPrefix, viewCountSuffix);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage) {
		super(perPage);
		setList(c);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage) {
		this((array != null ? Arrays.asList(array) : null), perPage);
	}

	@Override
	public List<T> loadViewList(int first, int count) {
		if (list == null) {
			return null;
		}
		List<T> subList = new ArrayList<T>();
		int last = Math.min(first + count, getTotalSize());
		int i = 0;
		for (T child : list) {
			if (i >= first && i < last) {
				subList.add(child);
			}
			i++;
		}
		return subList;
	}

	@Override
	public int loadTotalSize() {
		return (list != null ? list.size() : 0);
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
	 * Set List
	 * 
	 * @param list
	 *            Collection
	 */
	public void setList(Collection<T> list) {
		this.list = list;

		initialize();
		setCurrentIndex(0);
	}

}