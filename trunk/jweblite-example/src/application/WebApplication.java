package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebApplication {

	private static final long serialVersionUID = 1L;

	private String startedAt = null;

	/**
	 * Default constructor.
	 */
	public WebApplication() {
		super();
		this.startedAt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Date());
	}

	public String getStartedAt() {
		return startedAt;
	}

}