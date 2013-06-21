package jweblite.util.el;

import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonELUtils {

	/**
	 * ========================================================================
	 * org.json.*
	 * ========================================================================
	 */

	/**
	 * To Json Object String
	 * 
	 * @param map
	 *            Map{String, ?}
	 * @return String
	 */
	public static String toJsonObjectString(Map<String, ?> map) {
		if (map == null) {
			return "";
		}
		return new JSONObject(map).toString();
	}

	/**
	 * To Json Array String
	 * 
	 * @param c
	 *            Collection{?}
	 * @return String
	 */
	public static String toJsonArrayString(Collection<?> c) {
		if (c == null) {
			return "";
		}
		return new JSONArray(c).toString();
	}

}