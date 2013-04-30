import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloSession implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloSession() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		context.getSession().setAttribute("test", "Session");
	}

}