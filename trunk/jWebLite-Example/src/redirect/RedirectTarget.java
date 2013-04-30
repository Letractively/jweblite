package redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class RedirectTarget implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RedirectTarget() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		HttpServletRequest req = context.getRequest();
		HttpServletResponse resp = context.getResponse();
		fm.setParameter("test", "Redirect");
		try {
			req.getRequestDispatcher("/HelloRedirect.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SkipException();
	}

}