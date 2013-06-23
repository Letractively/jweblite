package jweblite.web.tag.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.data.provider.LoopIterator;
import jweblite.util.StringUtils;

public class AlternatingTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String var = null;
	private String data = null;
	private String separator = null;

	/**
	 * Default constructor.
	 */
	public AlternatingTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		// var
		if (this.var != null) {
			LoopIterator<String> loopIt = new LoopIterator<String>(
					StringUtils.split(this.data,
							(this.separator != null ? this.separator : ",")));
			this.pageContext.setAttribute(this.var, loopIt);
		}
		return TagSupport.EVAL_PAGE;
	}

	/**
	 * Get Var
	 * 
	 * @return String
	 */
	public String getVar() {
		return var;
	}

	/**
	 * Set Var
	 * 
	 * @param var
	 *            String
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * Get Data
	 * 
	 * @return String
	 */
	public String getData() {
		return data;
	}

	/**
	 * Set Data
	 * 
	 * @param data
	 *            String
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Get Separator
	 * 
	 * @return String
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * Set Separator
	 * 
	 * @param separator
	 *            String
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

}