import java.util.Arrays;
import java.util.List;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class HelloELTagLib implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private List<Integer> numberList = Arrays.asList(new Integer[] { 0, 1, 2,
			3, 4, 5 });

	/**
	 * Default constructor.
	 */
	public HelloELTagLib() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

}