package jweblite.web.extension.paging;

import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.data.provider.DataProvider;

public class PagesTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Boolean test = null;

	private String var = null;
	private String selected = null;
	private String empty = null;

	// private
	private String index = null;
	private DataProvider provider = null;
	private Iterator<Integer> indexIterator = null;
	private Iterator dataIterator = null;

	/**
	 * Default constructor.
	 */
	public PagesTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {
		Tag tag = this.getParent();
		if (tag == null || !(tag instanceof PagingTag)) {
			throw new JspTagException("parent tag error");
		}
		PagingTag parent = (PagingTag) tag;
		this.index = parent.getIndex();
		this.provider = parent.getProvider();
		if (provider != null) {
			try {
				this.indexIterator = this.provider.getViewIndexList()
						.iterator();
				this.dataIterator = this.provider.getViewList().iterator();
			} catch (Exception e) {
				throw new JspTagException("iterate pages tag error");
			}
		}
		// iterator
		if (this.iterate()) {
			return TagSupport.EVAL_BODY_INCLUDE;
		}
		return TagSupport.SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		// iterator
		if (this.iterate()) {
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
		if (this.test == null) {
			if (this.provider == null || this.indexIterator == null
					|| !this.indexIterator.hasNext()) {
				return false;
			}
		} else if (!this.test.booleanValue()) {
			return false;
		}
		// var
		boolean hasNext = (this.dataIterator != null && this.dataIterator
				.hasNext());
		Object data = (hasNext ? this.dataIterator.next() : null);
		if (this.var != null) {
			this.pageContext.setAttribute(this.var, data);
		}
		// index
		int index = (this.indexIterator != null && this.indexIterator.hasNext() ? this.indexIterator
				.next() : null);
		if (this.index != null) {
			this.pageContext.setAttribute(this.index, index);
		}
		// selected
		int currentIndex = (this.provider != null ? this.provider
				.getCurrentIndex() : 0);
		if (this.selected != null) {
			this.pageContext.setAttribute(this.selected,
					(index == currentIndex));
		}
		// empty
		if (this.empty != null) {
			this.pageContext.setAttribute(this.empty, !hasNext);
		}
		return true;
	}

	@Override
	public int doEndTag() throws JspException {
		// var
		if (this.var != null) {
			this.pageContext.removeAttribute(this.var);
		}
		// selected
		if (this.selected != null) {
			this.pageContext.removeAttribute(this.selected);
		}
		// empty
		if (this.empty != null) {
			this.pageContext.removeAttribute(this.empty);
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

	/**
	 * Get Empty
	 * 
	 * @return String
	 */
	public String getEmpty() {
		return empty;
	}

	/**
	 * Set Empty
	 * 
	 * @param empty
	 *            String
	 */
	public void setEmpty(String empty) {
		this.empty = empty;
	}

}