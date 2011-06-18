package jweblite.web.application;

import java.io.Serializable;

public interface JWebLiteApplicationBoundListener extends Serializable {

	/**
	 * Bound
	 */
	public void bound();

	/**
	 * Unbound
	 */
	public void unbound();

}