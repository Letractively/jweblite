package jweblite.data.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CollectionDataProvider<T> extends DataProvider<T> {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private Collection<T> list = null;

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage) {
		this(Arrays.asList(array), perPage);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage) {
		super(perPage);
		this.setList(c);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(T[] array, int perPage, int viewCountPrefix,
			int viewCountSuffix) {
		this(Arrays.asList(array), perPage, viewCountPrefix, viewCountSuffix);
	}

	/**
	 * Default constructor.
	 */
	public CollectionDataProvider(Collection<T> c, int perPage,
			int viewCountPrefix, int viewCountSuffix) {
		super(perPage, viewCountPrefix, viewCountSuffix);
		this.setList(c);
	}

	@Override
	public List<T> loadViewList(int first, int count) {
		if (this.list == null) {
			return null;
		}
		List<T> subList = new ArrayList<T>();
		int last = Math.min(first + count, this.getTotalSize());
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
		return (this.list != null ? this.list.size() : 0);
	}

	public Collection<T> getList() {
		return list;
	}

	public void setList(Collection<T> list) {
		this.list = list;
		this.initialize();
		this.setCurrentIndex(0);
	}

}