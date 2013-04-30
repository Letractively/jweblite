package jweblite.web.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import jweblite.util.StringUtils;
import jweblite.util.callback.AttributeCallback;

import org.apache.commons.lang.StringEscapeUtils;

public class HtmlTag extends BodyTagSupport implements DynamicAttributes {
	private static final long serialVersionUID = 1L;

	private AttributeCallback additionAttrValueRenderer = null;

	private final Map<String, Object> originalAdditionalAttrMap = new HashMap();
	private final AttributeCallback defaultAdditionAttrValueRenderer = new AttributeCallback() {
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
		AttributeCallback additionAttrValueRenderer = this.additionAttrValueRenderer;
		if (additionAttrValueRenderer == null) {
			additionAttrValueRenderer = this.defaultAdditionAttrValueRenderer;
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
			if (additionAttrValueRenderer != null) {
				attrValue = additionAttrValueRenderer.callback(attrName,
						attrValue);
			}
			if (attrValue != null) {
				String attr = String
						.format("%s=\"%s\"", attrName, StringEscapeUtils
								.escapeHtml(String.valueOf(attrValue)));
				result.add(attr);
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
	 * Get Addition Attr Value Renderer
	 * 
	 * @return AttributeCallback
	 */
	public AttributeCallback getAdditionAttrValueRenderer() {
		return additionAttrValueRenderer;
	}

	/**
	 * Set Addition Attr Value Renderer
	 * 
	 * @param additionAttrValueRenderer
	 *            AttributeCallback
	 */
	public void setAdditionAttrRenderer(
			AttributeCallback additionAttrValueRenderer) {
		this.additionAttrValueRenderer = additionAttrValueRenderer;
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