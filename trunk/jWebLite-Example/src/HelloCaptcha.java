import jweblite.extension.resource.image.CaptchaImage;
import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloCaptcha implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private String test = null;

	/**
	 * Default constructor.
	 */
	public HelloCaptcha() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		this.test = CaptchaImage.createChallenge(context.getRequest(),
				String.valueOf((int) (Math.random() * 10000)));
	}

	public String getTest() {
		return test;
	}

}