import jweblite.web.JWebLitePage;
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
	public boolean doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		req.getSession().setAttribute("test", "Session");
		return false;
	}

}