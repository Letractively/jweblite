package jweblite.util.callback;

import javax.servlet.http.HttpSession;

public interface HttpSessionCallback extends Callback {

	/**
	 * Callback
	 * 
	 * @param session
	 *            HttpSession
	 */
	public void callback(HttpSession session);

}