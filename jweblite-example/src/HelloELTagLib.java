import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public class HelloELTagLib implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private List<Integer> numberList = Arrays.asList(new Integer[] { 0, 1, 2,
			3, 4, 5 });

	public HelloELTagLib() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		return false;
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

}