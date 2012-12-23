import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

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

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel fm) throws SkipException {
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

}