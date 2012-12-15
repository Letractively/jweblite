package redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

public class RedirectTarget implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RedirectTarget() {
		super();
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
		formModel.putParameter("test", "Redirect");
		try {
			req.getRequestDispatcher("/HelloRedirect.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SkipException();
	}

}