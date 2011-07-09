package jweblite.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.stream.JsonWriter;

public class JsonUtils {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * To Json Array
	 * 
	 * @param out
	 *            Writer
	 * @param c
	 *            Collection
	 * @param htmlSafe
	 *            boolean
	 */
	public static void toJsonArray(Writer out, Collection c, boolean htmlSafe) {
		if (out == null || c == null) {
			return;
		}
		JsonWriter jw = null;
		try {
			jw = new JsonWriter(out);
			jw.setHtmlSafe(htmlSafe);
			jw.beginArray();
			// body
			for (Object value : c) {
				if (value == null) {
					jw.nullValue();
				} else if (value instanceof Boolean) {
					jw.value((Boolean) value);
				} else if (value instanceof Number) {
					jw.value((Number) value);
				} else {
					jw.value((String) value);
				}
			}
			jw.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(jw);
		}
	}

	/**
	 * To Json Array
	 * 
	 * @param c
	 *            Collection
	 * @param htmlSafe
	 *            boolean
	 * @return String
	 */
	public static String toJsonArray(Collection c, boolean htmlSafe) {
		if (c == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		toJsonArray(sw, c, htmlSafe);
		return sw.toString();
	}

	/**
	 * To Json Object
	 * 
	 * @param out
	 *            Writer
	 * @param map
	 *            Map
	 * @param htmlSafe
	 *            boolean
	 */
	public static void toJsonObject(Writer out, Map<String, ?> map,
			boolean htmlSafe) {
		if (out == null || map == null) {
			return;
		}
		JsonWriter jw = null;
		try {
			jw = new JsonWriter(out);
			jw.setHtmlSafe(htmlSafe);
			jw.beginObject();
			// body
			for (String key : map.keySet()) {
				Object value = map.get(key);
				jw.name(key);
				if (value == null) {
					jw.nullValue();
				} else if (value instanceof Boolean) {
					jw.value((Boolean) value);
				} else if (value instanceof Number) {
					jw.value((Number) value);
				} else {
					jw.value((String) value);
				}
			}
			jw.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(jw);
		}
	}

	/**
	 * To Json Object
	 * 
	 * @param map
	 *            Map
	 * @param htmlSafe
	 *            boolean
	 * @return String
	 */
	public static String toJsonObject(Map<String, ?> map, boolean htmlSafe) {
		if (map == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		toJsonObject(sw, map, htmlSafe);
		return sw.toString();
	}

}