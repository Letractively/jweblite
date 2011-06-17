import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public class HelloSession implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloSession() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		req.getSession().setAttribute("test", "Session");
		return false;
	}

}