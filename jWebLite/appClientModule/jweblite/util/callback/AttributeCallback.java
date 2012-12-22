package jweblite.util.callback;

public interface AttributeCallback {

	/**
	 * Callback
	 * 
	 * @param attrName
	 *            String
	 * @param attrValue
	 *            Object
	 * @return Object
	 */
	public Object callback(String attrName, Object attrValue);

}