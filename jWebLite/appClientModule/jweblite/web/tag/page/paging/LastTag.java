package jweblite.web.tag.page.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.data.provider.DataProvider;

public class LastTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private Boolean test = null;

	/**
	 * Default constructor.
	 */
	public LastTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {
		Tag tag = getParent();
		if (tag == null || !(tag instanceof PagingTag)) {
			throw new JspTagException("parent tag error");
		}
		PagingTag parent = (PagingTag) tag;
		DataProvider<?> provider = parent.getProvider();
		int lastIndex = (provider != null ? provider.getTotalPageCount() - 1
				: 0);
		// test
		if (test == null) {
			if (provider == null || provider.getCurrentIndex() + 2 > lastIndex) {
				return TagSupport.SKIP_BODY;
			}
		} else if (!test.booleanValue()) {
			return TagSupport.SKIP_BODY;
		}
		// index
		if (parent.getIndex() != null) {
			pageContext.setAttribute(parent.getIndex(), lastIndex);
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}

	/**
	 * Get Test
	 * 
	 * @return Boolean
	 */
	public Boolean getTest() {
		return test;
	}

	/**
	 * Set Test
	 * 
	 * @param test
	 *            Boolean
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}

}