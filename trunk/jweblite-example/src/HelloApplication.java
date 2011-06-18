import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public class HelloApplication implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String initClassName = null;
	private String test = null;

	public HelloApplication() {
		super();
		Object initClass = JWebLiteApplication.get().getInitClass();

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