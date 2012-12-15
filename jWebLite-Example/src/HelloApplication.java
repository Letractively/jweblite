import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.FormModel;
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

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
	}

	public String getInitClassName() {
		return initClassName;
	}

	public String getTest() {
		return test;
	}

}