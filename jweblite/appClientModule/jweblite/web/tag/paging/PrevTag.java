package jweblite.web.tag.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jweblite.data.provider.DataProvider;

public class PrevTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private Boolean test = null;

	/**
	 * Default constructor.
	 */
	public PrevTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {
		Tag tag = this.getParent();
		if (tag == null || !(tag instanceof PagingTag)) {
			throw new JspTagException("parent tag error");
		}
		PagingTag parent = (PagingTag) tag;
		DataProvider provider = parent.getProvider();
		int previousIndex = (provider != null ? provider.getCurrentIndex() - 1
				: 0);
		// test
		if (this.test == null) {
			if (provider == null || !provider.isHasPrevious()) {
				return TagSupport.SKIP_BODY;
			}
		} else if (!this.test.booleanValue()) {
			return TagSupport.SKIP_BODY;
		}
		// index
		if (parent.getIndex() != null) {
			this.pageContext.setAttribute(parent.getIndex(), previousIndex);
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