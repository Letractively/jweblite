package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import jweblite.web.JWebLiteApplication;

public class WebApplication extends JWebLiteApplication {
	private static final long serialVersionUID = 1L;

	private String startedAt = null;

	/**
	 * Default constructor.
	 */
	public WebApplication() {
		this.startedAt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Date());
	}

	public String getStartedAt() {
		return startedAt;
	}

}