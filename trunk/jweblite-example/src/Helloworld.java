import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLiteInterface;
import jweblite.web.JWebLiteRequestWrapper;

public class Helloworld implements JWebLiteInterface {

	private String test = null;

	public Helloworld() {
		super();
	}

	@Override
	public void doRequest(JWebLiteRequestWrapper req, HttpServletResponse resp) {
		this.test = req.getParameter("test", "World");
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}