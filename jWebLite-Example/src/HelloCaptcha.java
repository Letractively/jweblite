import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.extension.resource.image.CaptchaImage;
import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

public class HelloCaptcha implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloCaptcha() {
		super();
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel fm) throws SkipException {
		this.test = CaptchaImage.createChallenge(req,
				String.valueOf((int) (Math.random() * 10000)));
	}

	public String getTest() {
		return test;
	}

}