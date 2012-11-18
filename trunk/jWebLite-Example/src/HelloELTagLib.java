import java.util.Arrays;
import java.util.List;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

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

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

}