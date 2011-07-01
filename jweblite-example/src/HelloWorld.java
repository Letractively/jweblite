import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class HelloWorld implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloWorld() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		this.test = req.getHtmlParameter("test", "World");
		return false;
	}

	public String getTest() {
		return test;
	}

}