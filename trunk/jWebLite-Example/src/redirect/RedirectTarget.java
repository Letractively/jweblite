package redirect;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class RedirectTarget implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RedirectTarget() {
		super();
	}

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		req.putParameter("test", "Redirect");
		try {
			req.getRequestDispatcher("/HelloRedirect.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SkipException();
	}

}