import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLiteInterface;

public class Index implements JWebLiteInterface {

	private String test = "";

	public Index() {
		super();
	}

	@Override
	public void doRequest(HttpServletRequest arg0, HttpServletResponse arg1) {
		this.test = "World";
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}