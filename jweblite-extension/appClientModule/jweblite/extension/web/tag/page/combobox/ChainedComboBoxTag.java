package jweblite.extension.web.tag.page.combobox;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jweblite.data.MultiValueMap;
import jweblite.util.JsonUtils;
import jweblite.util.StringUtils;
import jweblite.web.tag.HtmlTag;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChainedComboBoxTag extends HtmlTag {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(ChainedComboBoxTag.class);

	private String eid = null;
	private String toEid = null;
	private MultiValueMap map = null;

	/**
	 * Default constructor.
	 */
	public ChainedComboBoxTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter jw = this.pageContext.getOut();
			// element
			jw.print(String.format("<select id='%s'", this.eid));
			// additional tag attrs
			Map<String, Object> additionalAttrMap = new HashMap();
			additionalAttrMap.put("onchange", StringUtils.format(
					"$1;$2Func(typeof($3Func)=='function'?$3Func:null);", "$",
					StringUtils.getStringValue((String) this
							.getOriginalAdditionalAttrMap().get("onchange"),
							"", true), this.eid, this.toEid));
			jw.print(" ");
			jw.print(this.makeAdditionalTagAttr(additionalAttrMap));
			jw.println(">");
			String content = StringUtils.getStringValue(
					(this.bodyContent != null ? this.bodyContent.getString()
							: null), null, true);
			if (content == null && this.map != null) {
				for (Object key : this.map.keySet()) {
					jw.print("<option>");
					jw.print(key);
					jw.println("</option>");
				}
			}
			jw.println("</select>");
			// js mapping
			jw.println("<script type=\"text/javascript\">//<![CDATA[");
			InputStream is = null;
			try {
				Class thisClass = this.getClass();
				is = thisClass.getResourceAsStream(thisClass.getSimpleName()
						.concat(".js"));
				String source = IOUtils.toString(is, "UTF-8");
				jw.println(StringUtils.format(source, "$", this.eid,
						this.toEid, JsonUtils.toJsonObject(this.map, false)));
			} catch (Exception e) {
				throw e;
			} finally {
				IOUtils.closeQuietly(is);
			}
			jw.println("//]]></script>");
		} catch (Exception e) {
			_cat.warn("Do end tag failed!", e);
		}
		return BodyTagSupport.EVAL_PAGE;
	}

	/**
	 * Get Eid
	 * 
	 * @return String
	 */
	public String getEid() {
		return eid;
	}

	/**
	 * Set Eid
	 * 
	 * @param eid
	 *            String
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	/**
	 * Get To Eid
	 * 
	 * @return String
	 */
	public String getToEid() {
		return toEid;
	}

	/**
	 * Set To Eid
	 * 
	 * @param toEid
	 *            String
	 */
	public void setToEid(String toEid) {
		this.toEid = toEid;
	}

	/**
	 * Get Map
	 * 
	 * @return MultiValueMap
	 */
	public MultiValueMap getMap() {
		return map;
	}

	/**
	 * Set Map
	 * 
	 * @param map
	 *            MultiValueMap
	 */
	public void setMap(MultiValueMap map) {
		this.map = map;
	}

}