import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.data.provider.CollectionDataProvider;
import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

public class PagingNavigation implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private CollectionDataProvider<String> dataProvider = new CollectionDataProvider<String>(
			new String[] { "Anthony Bautista", "Lisa Drennan", "Adam Boggess",
					"Nora Velarde", "Michael Stites", "Jeannette Waits",
					"Jeff Rapp", "Nora Melvin", "Agnes Minnich",
					"Johnny Casarez" }, 3);

	/**
	 * Default constructor.
	 */
	public PagingNavigation() {
		super();
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
		this.dataProvider.setCurrentIndex(formModel.getInt("page", 0));
	}

	public CollectionDataProvider<String> getDataProvider() {
		return dataProvider;
	}

}