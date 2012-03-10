import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;
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

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
	}

	public String getInitClassName() {
		return initClassName;
	}

	public String getTest() {
		return test;
	}

}