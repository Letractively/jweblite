import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class HelloIncludeSubpage implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = "Subpage";

	/**
	 * Default constructor.
	 */
	public HelloIncludeSubpage() {
		super();
	}

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
	}

	public String getTest() {
		return test;
	}

}