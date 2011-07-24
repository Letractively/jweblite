package jweblite.web.session;

import javax.servlet.http.HttpSession;

import jweblite.util.callback.Callback;

public interface HttpSessionCallback extends Callback {

	/**
	 * Callback
	 * 
	 * @param session
	 *            HttpSession
	 */
	public void callback(HttpSession session);

}