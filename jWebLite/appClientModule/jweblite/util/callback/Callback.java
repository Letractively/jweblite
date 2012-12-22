package jweblite.util.callback;

public interface Callback<V> {

	/**
	 * Callback
	 * 
	 * @param v
	 *            V
	 * @return Object
	 */
	public V callback(V v);

}