package jweblite.web.tag.page.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.data.provider.DataProvider;

public class PagingTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private DataProvider<?> provider = null;

	private String index = null;
	private String var = null;
	private String total = null;

	/**
	 * Default constructor.
	 */
	public PagingTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {
		// init
		if (provider == null) {
			return TagSupport.SKIP_BODY;
		}
		// var
		if (var != null) {
			pageContext.setAttribute(var, provider);
		}
		// total
		if (total != null) {
			pageContext.setAttribute(total, provider.getTotalPageCount());
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		// index
		if (index != null) {
			pageContext.removeAttribute(index);
		}
		// var
		if (var != null) {
			pageContext.removeAttribute(var);
		}
		// total
		if (total != null) {
			pageContext.removeAttribute(total);
		}
		return TagSupport.EVAL_PAGE;
	}

	/**
	 * Get Provider
	 * 
	 * @return DataProvider
	 */
	public DataProvider<?> getProvider() {
		return provider;
	}

	/**
	 * Set Provider
	 * 
	 * @param provider
	 *            DataProvider
	 */
	public void setProvider(DataProvider<?> provider) {
		this.provider = provider;
	}

	/**
	 * Get Index
	 * 
	 * @return String
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Set Index
	 * 
	 * @param index
	 *            String
	 */
	public void setIndex(String index) {
		this.index = index;
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
	 * Get Total
	 * 
	 * @return String
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Set Total
	 * 
	 * @param total
	 *            String
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}