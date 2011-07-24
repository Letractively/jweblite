package jweblite.web.tag;

import jweblite.util.callback.Callback;

public interface AdditionalTagAttrValueCallback extends Callback {

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