package jweblite.util.callback;

public interface AdditionalTagAttrCallback extends Callback {

	/**
	 * Callback
	 * 
	 * @param localName
	 *            String
	 * @param value
	 *            Object
	 * @return Object
	 */
	public Object callback(String localName, Object value);

}