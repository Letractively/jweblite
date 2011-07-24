package jweblite.web.tag;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jweblite.web.wrapper.JWebLiteResponseWrapper;
import jweblite.web.wrapper.stream.JWebLiteProxyResponseWrapperStream;
import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IncludeTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

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
			ServletRequest request = this.pageContext.getRequest();
			JWebLiteResponseWrapper respWrapper = (JWebLiteResponseWrapper) this.pageContext
					.getResponse();

			// original wrapper stream
			JWebLiteResponseWrapperStream oriWrapperStream = respWrapper
					.getWrapperStream();
			// proxy
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			respWrapper
					.setWrapperStream(new JWebLiteProxyResponseWrapperStream(
							baos));
			request.getRequestDispatcher(this.page).forward(request,
					respWrapper);
			// revert
			respWrapper.setWrapperStream(oriWrapperStream);
			// insert output
			this.pageContext.getOut().write(baos.toString());
		} catch (Exception e) {
			this.log.warn("Do end tag failed!", e);
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