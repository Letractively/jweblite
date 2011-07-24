import jweblite.web.JWebLitePage;
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

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		return false;
	}

	public String getTest() {
		return test;
	}

}