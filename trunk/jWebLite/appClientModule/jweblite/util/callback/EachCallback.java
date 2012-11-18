package jweblite.util.callback;

public interface EachCallback<T> extends Callback {

	/**
	 * Callback
	 * 
	 * @param t
	 *            T
	 * @param index
	 *            int
	 * @return Object
	 */
	public Object callback(T t, int index);

}