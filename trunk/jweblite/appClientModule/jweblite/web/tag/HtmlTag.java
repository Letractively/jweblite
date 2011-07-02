package jweblite.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private final Map<String, Object> additionalAttrMap = new HashMap();

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
	 * Get Additional Attr Map
	 * 
	 * @return Map
	 */
	public Map<String, Object> getAdditionalAttrMap() {
		return additionalAttrMap;
	}

}