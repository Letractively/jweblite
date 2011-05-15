package jweblite.util.callback;

public interface SimpleCallback<T> extends Callback<T> {

	/**
	 * Callback
	 * 
	 * @return T
	 */
	public T callback();

}