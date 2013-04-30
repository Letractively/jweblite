import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloWorld implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloWorld() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		this.test = fm.getEscapedString("test", "World");
	}

	public String getTest() {
		return test;
	}

}