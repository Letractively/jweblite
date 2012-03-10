import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
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

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		this.test = req.getHtmlParameter("test", "World");
	}

	public String getTest() {
		return test;
	}

}