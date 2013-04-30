import jweblite.web.JWebLiteApplication;
import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;
import application.WebApplication;

public class HelloApplication implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private String initClassName = null;
	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloApplication() {
		super();
		JWebLiteApplication application = JWebLiteApplication.get();

		this.initClassName = application.getClass().getName();
		this.test = ((WebApplication) application).getStartedAt();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
	}

	public String getInitClassName() {
		return initClassName;
	}

	public String getTest() {
		return test;
	}

}