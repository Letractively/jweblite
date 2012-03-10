import jweblite.extension.resource.image.CaptchaImage;
import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class HelloCaptcha implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloCaptcha() {
		super();
	}

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		this.test = CaptchaImage.createChallenge(req,
				String.valueOf((int) (Math.random() * 10000)));
	}

	public String getTest() {
		return test;
	}

}