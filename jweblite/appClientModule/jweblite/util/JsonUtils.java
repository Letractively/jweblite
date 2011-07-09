package jweblite.util;

import java.io.IOException;
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
	 * To Json Value
	 * 
	 * @param jw
	 *            JsonWriter
	 * @param o
	 *            Object
	 * @throws IOException
	 */
	public static void toJsonValue(JsonWriter jw, Object o) throws IOException {
		if (jw == null) {
			return;
		}
		// body
		if (o == null) {
			jw.nullValue();
		} else if (o instanceof Boolean) {
			jw.value((Boolean) o);
		} else if (o instanceof Number) {
			jw.value((Number) o);
		} else if (o instanceof Collection) {
			toJsonArray(jw, (Collection) o);
		} else if (o instanceof Map) {
			toJsonObject(jw, (Map) o);
		} else {
			jw.value(String.valueOf(o));
		}
	}

	/**
	 * To Json Value
	 * 
	 * @param out
	 *            Writer
	 * @param o
	 *            Object
	 * @param htmlSafe
	 *            boolean
	 */
	public static void toJsonValue(Writer out, Object o, boolean htmlSafe) {
		if (out == null) {
			return;
		}
		JsonWriter jw = null;
		try {
			jw = new JsonWriter(out);
			jw.setHtmlSafe(htmlSafe);
			toJsonValue(jw, o);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(jw);
		}
	}

	/**
	 * To Json Value
	 * 
	 * @param o
	 *            Object
	 * @param htmlSafe
	 *            boolean
	 * @return String
	 */
	public static String toJsonValue(Object o, boolean htmlSafe) {
		StringWriter sw = new StringWriter();
		toJsonValue(sw, o, htmlSafe);
		return sw.toString();
	}

	/**
	 * To Json Array
	 * 
	 * @param jw
	 *            JsonWriter
	 * @param c
	 *            Collection
	 * @throws IOException
	 */
	public static void toJsonArray(JsonWriter jw, Collection c)
			throws IOException {
		if (jw == null || c == null) {
			return;
		}
		jw.beginArray();
		// body
		for (Object value : c) {
			toJsonValue(jw, value);
		}
		jw.endArray();
	}

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
			toJsonArray(jw, c);
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
			return "null";
		}
		StringWriter sw = new StringWriter();
		toJsonArray(sw, c, htmlSafe);
		return sw.toString();
	}

	/**
	 * To Json Object
	 * 
	 * @param jw
	 *            JsonWriter
	 * @param map
	 *            Map
	 * @throws IOException
	 */
	public static void toJsonObject(JsonWriter jw, Map<String, ?> map)
			throws IOException {
		if (jw == null || map == null) {
			return;
		}
		jw.beginObject();
		// body
		for (String key : map.keySet()) {
			Object value = map.get(key);
			jw.name(key);
			toJsonValue(jw, value);
		}
		jw.endObject();
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
			toJsonObject(jw, map);
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
			return "null";
		}
		StringWriter sw = new StringWriter();
		toJsonObject(sw, map, htmlSafe);
		return sw.toString();
	}

}