package error;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class ErrorServlet implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ErrorServlet() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		throw new RuntimeException();
	}

}