package jweblite.util.callback;

public interface EachCallback<V> {

	/**
	 * Callback
	 * 
	 * @param v
	 *            V
	 * @param index
	 *            int
	 * @return Object
	 */
	public Object callback(V v, int index);

}