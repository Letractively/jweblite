package jweblite.web.tag.page.paging;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.data.provider.DataProvider;

public class PagesTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private Boolean test = null;
	private String selected = null;

	// private
	private String index = null;
	private int currentIndex = -1;
	private Iterator<Integer> indexIterator = null;

	/**
	 * Default constructor.
	 */
	public PagesTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {
		Tag tag = getParent();
		if (tag == null || !(tag instanceof PagingTag)) {
			throw new JspTagException("parent tag error");
		}
		PagingTag parent = (PagingTag) tag;
		index = parent.getIndex();
		DataProvider<?> provider = parent.getProvider();
		if (provider != null) {
			currentIndex = provider.getCurrentIndex();
			List<Integer> viewIndexList = provider.getViewIndexList();
			if (viewIndexList != null) {
				indexIterator = viewIndexList.iterator();
			}
		}
		// iterator
		if (iterate()) {
			return TagSupport.EVAL_BODY_INCLUDE;
		}
		return TagSupport.SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		// iterator
		if (iterate()) {
			return TagSupport.EVAL_BODY_AGAIN;
		}
		return TagSupport.SKIP_BODY;
	}

	/**
	 * Iterate
	 * 
	 * @return boolean
	 */
	public boolean iterate() {
		// test
		if (test == null) {
			if (indexIterator == null || !indexIterator.hasNext()) {
				return false;
			}
		} else if (!test.booleanValue()) {
			return false;
		}
		// index
		int nextIndex = (indexIterator != null && indexIterator.hasNext() ? indexIterator
				.next() : null);
		if (index != null) {
			pageContext.setAttribute(index, nextIndex);
		}
		// selected
		if (selected != null) {
			pageContext.setAttribute(selected, (nextIndex == currentIndex));
		}
		return true;
	}

	@Override
	public int doEndTag() throws JspException {
		// selected
		if (selected != null) {
			pageContext.removeAttribute(selected);
		}
		return TagSupport.EVAL_PAGE;
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

	/**
	 * Get Selected
	 * 
	 * @return String
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * Set Selected
	 * 
	 * @param selected
	 *            String
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

}