import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloIncludeSubpage implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private String test = "subpage";

	/**
	 * Default constructor.
	 */
	public HelloIncludeSubpage() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
	}

	public String getTest() {
		return test;
	}

}