package jweblite.web.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import jweblite.util.StringUtils;
import jweblite.util.callback.AdditionalTagAttrCallback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private AdditionalTagAttrCallback additionAttrCallback = null;

	private final Map<String, Object> additionalAttrMap = new HashMap();
	private final AdditionalTagAttrCallback defaultAdditionAttrCallback = new AdditionalTagAttrCallback() {
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
		this.additionalAttrMap.put(localName, value);
	}

	/**
	 * Make Additional Tag Attr
	 * 
	 * @return String
	 */
	public String makeAdditionalTagAttr() {
		if (this.additionalAttrMap == null) {
			return "";
		}
		AdditionalTagAttrCallback additionAttrCallback = this
				.getAdditionAttrCallback();
		if (additionAttrCallback == null) {
			additionAttrCallback = this.defaultAdditionAttrCallback;
		}
		List<String> result = new ArrayList();
		for (String attrName : this.additionalAttrMap.keySet()) {
			Object attrValue = this.additionalAttrMap.get(attrName);
			if (additionAttrCallback != null) {
				attrValue = additionAttrCallback.callback(attrName, attrValue);
			}
			if (attrValue != null) {
				result.add(String.format("%s=\"%s\"", attrName, attrValue));
			}
		}
		return StringUtils.join(result, " ");
	}

	/**
	 * Get Addition Attr Callback
	 * 
	 * @return AdditionalTagAttrCallback
	 */
	public AdditionalTagAttrCallback getAdditionAttrCallback() {
		return additionAttrCallback;
	}

	/**
	 * Set Addition Attr Callback
	 * 
	 * @param additionAttrCallback
	 *            AdditionalTagAttrCallback
	 */
	public void setAdditionAttrCallback(
			AdditionalTagAttrCallback additionAttrCallback) {
		this.additionAttrCallback = additionAttrCallback;
	}

	/**
	 * Get Additional Attr Map
	 * 
	 * @return Map
	 */
	public Map<String, Object> getAdditionalAttrMap() {
		return additionalAttrMap;
	}

}