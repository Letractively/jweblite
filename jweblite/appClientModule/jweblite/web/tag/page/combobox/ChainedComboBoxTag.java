package jweblite.web.tag.page.combobox;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jweblite.data.MultiValueMap;
import jweblite.util.JsonUtils;
import jweblite.util.StringUtils;
import jweblite.web.tag.HtmlTag;

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
			additionalAttrMap.put("onchange", String.format(
					"%s;%sFunc(typeof(%sFunc)=='function'?%sFunc:null);",
					StringUtils.getStringValue((String) this
							.getOriginalAdditionalAttrMap().get("onchange"),
							"", true), this.eid, this.toEid, this.toEid));
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
			jw.println("<script type=\"text/javascript\">");
			jw.println("//<![CDATA[");
			jw.println(String.format("var %sMap=%s;", this.eid,
					JsonUtils.toJsonObject(this.map, false)));
			jw.print(String.format("var %sFunc=function(callback){", this.eid)); // function-start
			jw.print(String
					.format("var obj=document.getElementById('%s');var toEle=document.getElementById('%s');",
							this.eid, this.toEid));
			jw.print("if(obj!=null&&obj.options!=null&&toEle!=null&&toEle.options!=null){"); // if-1
			jw.print("toEle.options.length=0;");
			jw.print("var toEleArray=null;");
			jw.print(String
					.format("if(%sMap!=null&&obj.options.length>0&&(toEleArray=%sMap[obj.options[obj.selectedIndex].value])!=null){",
							this.eid, this.eid)); // if-2
			jw.print("for(var i in toEleArray){"); // for-3
			jw.print("var toEleValue=toEleArray[i];");
			jw.print("if(toEleValue==null){continue;}");
			jw.print("var toEleOpt=document.createElement('option');");
			jw.print("if(typeof(toEleValue)=='object'){"); // if-4
			jw.print("toEleOpt.innerHTML=toEleValue.name;");
			jw.print("toEleOpt.value=toEleValue.value;");
			jw.print("}else{");
			jw.print("toEleOpt.innerHTML=toEleValue;");
			jw.print("}"); // end if-4
			jw.print("toEle.appendChild(toEleOpt);");
			jw.print("}"); // end for-3
			jw.print("}"); // end if-2
			jw.print("if(callback){callback();}");
			jw.print("}"); // end if-1
			jw.print("};"); // end function
			jw.println(String
					.format("if(typeof(%sParentFunc)=='function'){%sParentFunc();};var %sParentFunc=%sFunc;",
							this.eid, this.eid, this.toEid, this.eid));
			jw.println("//]]>");
			jw.println("</script>");
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