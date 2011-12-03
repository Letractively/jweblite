import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class HelloSession implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloSession() {
		super();
	}

	@Override
	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		req.getSession().setAttribute("test", "Session");
	}

}