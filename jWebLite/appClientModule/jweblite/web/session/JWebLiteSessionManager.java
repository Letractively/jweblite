package jweblite.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jweblite.util.callback.Callback;

public class JWebLiteSessionManager implements HttpSessionBindingListener,
		Serializable {
	private static final long serialVersionUID = 1L;

	private static JWebLiteSessionManager factory = new JWebLiteSessionManager();

	private int activationCount = 0;
	private Callback<HttpSession> boundEvent = null;
	private Callback<HttpSession> unboundEvent = null;

	/**
	 * Default constructor.
	 */
	private JWebLiteSessionManager() {
		super();
	}

	/**
	 * Get
	 * 
	 * @return JWebLiteSessionManager
	 */
	public static JWebLiteSessionManager get() {
		return factory;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		activationCount++;
		if (boundEvent != null) {
			boundEvent.callback(event.getSession());
		}
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		activationCount--;
		if (unboundEvent != null) {
			unboundEvent.callback(event.getSession());
		}
	}

	/**
	 * Get Bound Event
	 * 
	 * @return Callback{HttpSession}
	 */
	public Callback<HttpSession> getBoundEvent() {
		return boundEvent;
	}

	/**
	 * Set Bound Event
	 * 
	 * @param boundEvent
	 *            Callback{HttpSession}
	 */
	public void setBoundEvent(Callback<HttpSession> boundEvent) {
		this.boundEvent = boundEvent;
	}

	/**
	 * Get Unbound Event
	 * 
	 * @return Callback{HttpSession}
	 */
	public Callback<HttpSession> getUnboundEvent() {
		return unboundEvent;
	}

	/**
	 * Set Unbound Event
	 * 
	 * @param unboundEvent
	 *            Callback{HttpSession}
	 */
	public void setUnboundEvent(Callback<HttpSession> unboundEvent) {
		this.unboundEvent = unboundEvent;
	}

	/**
	 * Get Activation Count
	 * 
	 * @return int
	 */
	public int getActivationCount() {
		return activationCount;
	}

}