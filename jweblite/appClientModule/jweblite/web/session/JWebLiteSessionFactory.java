package jweblite.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jweblite.util.callback.HttpSessionCallback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteSessionFactory implements HttpSessionBindingListener,
		Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private static JWebLiteSessionFactory factory = new JWebLiteSessionFactory();

	private int activationCount = 0;
	private HttpSessionCallback boundEvent = null;
	private HttpSessionCallback unboundEvent = null;

	/**
	 * Default constructor.
	 */
	private JWebLiteSessionFactory() {
		super();
	}

	/**
	 * Get
	 * 
	 * @return JWebLiteSessionFactory
	 */
	public static JWebLiteSessionFactory get() {
		return factory;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		this.activationCount++;
		if (this.boundEvent != null) {
			this.boundEvent.callback(event.getSession());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		this.activationCount--;
		if (this.unboundEvent != null) {
			this.unboundEvent.callback(event.getSession());
		}
	}

	/**
	 * Get Bound Event
	 * 
	 * @return HttpSessionCallback
	 */
	public HttpSessionCallback getBoundEvent() {
		return boundEvent;
	}

	/**
	 * Set Bound Event
	 * 
	 * @param boundEvent
	 *            HttpSessionCallback
	 */
	public void setBoundEvent(HttpSessionCallback boundEvent) {
		this.boundEvent = boundEvent;
	}

	/**
	 * Get Unbound Event
	 * 
	 * @return HttpSessionCallback
	 */
	public HttpSessionCallback getUnboundEvent() {
		return unboundEvent;
	}

	/**
	 * Set Unbound Event
	 * 
	 * @param unboundEvent
	 *            HttpSessionCallback
	 */
	public void setUnboundEvent(HttpSessionCallback unboundEvent) {
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