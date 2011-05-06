import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.JWebLiteRequestWrapper;

public class HelloWorld implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloWorld() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		this.test = req.getParameter("test", "World");
		return false;
	}

	public String getTest() {
		return test;
	}

}