import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloRedirect implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloRedirect() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		this.test = fm.getEscapedString("test");
	}

	public String getTest() {
		return test;
	}

}