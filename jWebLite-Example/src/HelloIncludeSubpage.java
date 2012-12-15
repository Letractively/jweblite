import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

public class HelloIncludeSubpage implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = "Subpage";

	/**
	 * Default constructor.
	 */
	public HelloIncludeSubpage() {
		super();
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
	}

	public String getTest() {
		return test;
	}

}