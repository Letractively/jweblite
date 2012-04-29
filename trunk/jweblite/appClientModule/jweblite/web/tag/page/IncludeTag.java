package jweblite.web.tag.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

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
			JWebLiteRequestWrapper req = (JWebLiteRequestWrapper) this.pageContext
					.getRequest();
			JWebLiteResponseWrapper resp = (JWebLiteResponseWrapper) this.pageContext
					.getResponse();
			String pageData = JWebLiteApplication.get().getRequestDispatcher()
					.writePageAsString(req, resp, this.page);
			// output
			this.pageContext.getOut().write(pageData);
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