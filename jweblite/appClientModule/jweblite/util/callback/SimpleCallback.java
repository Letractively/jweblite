package jweblite.util.callback;

public interface SimpleCallback<T> extends Callback {

	/**
	 * Callback
	 * 
	 * @return T
	 */
	public T callback();

}