package jweblite.web.dispatcher;

import java.io.Serializable;

public class JWebLiteRequestDispatchSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	private String referenceClassName = null;
	private String referenceResourcePath = null;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings() {
		super();
	}

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(String referenceClassName,
			String referenceResourcePath) {
		super();
		this.referenceClassName = referenceClassName;
		this.referenceResourcePath = referenceResourcePath;
	}

	/**
	 * Get Reference Class Name
	 * 
	 * @return String
	 */
	public String getReferenceClassName() {
		return referenceClassName;
	}

	/**
	 * Set Reference Class Name
	 * 
	 * @param referenceClassName
	 *            String
	 */
	public void setReferenceClassName(String referenceClassName) {
		this.referenceClassName = referenceClassName;
	}

	/**
	 * Get Reference Resource Path
	 * 
	 * @return String
	 */
	public String getReferenceResourcePath() {
		return referenceResourcePath;
	}

	/**
	 * Set Reference Resource Path
	 * 
	 * @param referenceResourcePath
	 *            String
	 */
	public void setReferenceResourcePath(String referenceResourcePath) {
		this.referenceResourcePath = referenceResourcePath;
	}

}