package jweblite.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jweblite.util.callback.HttpSessionCallback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpSessionFactory implements HttpSessionBindingListener,
		Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private static HttpSessionFactory factory = new HttpSessionFactory();

	private int activationCount = 0;
	private HttpSessionCallback boundEventList = null;
	private HttpSessionCallback unboundEventList = null;

	/**
	 * Default constructor.
	 */
	private HttpSessionFactory() {
		super();
	}

	public static HttpSessionFactory get() {
		return factory;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		this.activationCount++;
		if (this.boundEventList != null) {
			synchronized (this) {
				this.boundEventList.callback(event.getSession());
			}
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		this.activationCount--;
		if (this.unboundEventList != null) {
			synchronized (this) {
				this.unboundEventList.callback(event.getSession());
			}
		}
	}

	/**
	 * Register Bound Event
	 * 
	 * @param callback
	 *            HttpSessionCallback
	 */
	public void registerBoundEvent(HttpSessionCallback callback) {
		this.boundEventList = callback;
	}

	/**
	 * Register Unbound Event
	 * 
	 * @param callback
	 *            HttpSessionCallback
	 */
	public void registerUnboundEvent(HttpSessionCallback callback) {
		this.unboundEventList = callback;
	}

	/**
	 * Unregister Bound Event
	 */
	public void unregisterBoundEvent() {
		this.boundEventList = null;
	}

	/**
	 * Unregister Unbound Event
	 */
	public void unregisterUnBoundEvent() {
		this.unboundEventList = null;
	}

	public int getActivationCount() {
		return activationCount;
	}

}