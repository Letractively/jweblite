package jweblite.web.tag.page;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.util.WebUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IncludeTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(IncludeTag.class);

	private String page = null;

	/**
	 * Default constructor.
	 */
	public IncludeTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			ServletRequest req = pageContext.getRequest();
			ServletResponse resp = pageContext.getResponse();
			String pageData = WebUtils.writePageAsString(req, resp, page);
			// output
			pageContext.getOut().write(pageData);
		} catch (Exception e) {
			_cat.warn("Do end tag failed!", e);
		}
		return TagSupport.EVAL_PAGE;
	}

	/**
	 * Get Page
	 * 
	 * @return String
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Set Page
	 * 
	 * @param page
	 *            String
	 */
	public void setPage(String page) {
		this.page = page;
	}

}