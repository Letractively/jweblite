package jweblite.web.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import jweblite.util.StringUtils;
import jweblite.util.callback.AdditionalTagAttrValueCallback;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private AdditionalTagAttrValueCallback additionAttrValueCallback = null;

	private final Map<String, Object> originalAdditionalAttrMap = new HashMap();
	private final AdditionalTagAttrValueCallback defaultAdditionAttrValueCallback = new AdditionalTagAttrValueCallback() {
		private static final long serialVersionUID = 1L;

		@Override
		public Object callback(String localName, Object value) {
			if (localName == null || value == null
					|| !(value instanceof Comparable)) {
				return null;
			}
			return value;
		}
	};

	/**
	 * Default constructor.
	 */
	public HtmlTag() {
		super();
	}

	@Override
	public void setDynamicAttribute(String uri, String localName, Object value)
			throws JspException {
		if (localName == null) {
			return;
		}
		this.originalAdditionalAttrMap.put(localName.toLowerCase(), value);
	}

	/**
	 * Make Additional Tag Attr
	 * 
	 * @param m
	 *            Map
	 * @return String
	 */
	public String makeAdditionalTagAttr(Map<String, Object> m) {
		AdditionalTagAttrValueCallback additionAttrValueCallback = this
				.getAdditionAttrValueCallback();
		if (additionAttrValueCallback == null) {
			additionAttrValueCallback = this.defaultAdditionAttrValueCallback;
		}
		List<String> result = new ArrayList();
		// prepare additionalAttrMap
		Map<String, Object> additionalAttrMap = new HashMap();
		additionalAttrMap.putAll(this.originalAdditionalAttrMap);
		if (m != null) {
			additionalAttrMap.putAll(m);
		}
		// to string
		for (String attrName : additionalAttrMap.keySet()) {
			Object attrValue = additionalAttrMap.get(attrName);
			if (additionAttrValueCallback != null) {
				attrValue = additionAttrValueCallback.callback(attrName,
						attrValue);
			}
			if (attrValue != null) {
				result.add(String
						.format("%s=\"%s\"", attrName, StringEscapeUtils
								.escapeHtml(String.valueOf(attrValue))));
			}
		}
		return StringUtils.join(result, " ");
	}

	/**
	 * Make Additional Tag Attr
	 * 
	 * @return String
	 */
	public String makeAdditionalTagAttr() {
		return this.makeAdditionalTagAttr(null);
	}

	/**
	 * Get Addition Attr Value Callback
	 * 
	 * @return AdditionalTagAttrValueCallback
	 */
	public AdditionalTagAttrValueCallback getAdditionAttrValueCallback() {
		return additionAttrValueCallback;
	}

	/**
	 * Set Addition Attr Value Callback
	 * 
	 * @param additionAttrValueCallback
	 *            AdditionalTagAttrValueCallback
	 */
	public void setAdditionAttrCallback(
			AdditionalTagAttrValueCallback additionAttrValueCallback) {
		this.additionAttrValueCallback = additionAttrValueCallback;
	}

	/**
	 * Get Original Additional Attr Map
	 * 
	 * @return Map
	 */
	public Map<String, Object> getOriginalAdditionalAttrMap() {
		return originalAdditionalAttrMap;
	}

}