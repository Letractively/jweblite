import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
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
		JWebLiteApplication initClass = JWebLiteApplication.get();

		this.initClassName = initClass.getClass().getName();
		this.test = ((WebApplication) initClass).getStartedAt();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		return false;
	}

	public String getInitClassName() {
		return initClassName;
	}

	public String getTest() {
		return test;
	}

}