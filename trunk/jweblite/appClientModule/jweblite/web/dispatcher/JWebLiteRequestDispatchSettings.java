package jweblite.web.dispatcher;

import java.io.Serializable;

public class JWebLiteRequestDispatchSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String originalServletPath;
	private String referenceClassName;
	private String referenceResourcePath;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(String originalServletPath,
			String referenceClassName, String referenceResourcePath) {
		super();
		this.originalServletPath = originalServletPath;
		this.referenceClassName = referenceClassName;
		this.referenceResourcePath = referenceResourcePath;
	}

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(String originalServletPath) {
		this(originalServletPath, null, null);
	}

	@Override
	public String toString() {
		return String
				.format("JWebLiteRequestDispatchSettings [ OriServletPath: %s RefResourcePath: %s, RefClassName: %s ]",
						this.originalServletPath, this.referenceResourcePath,
						this.referenceClassName);
	}

	/**
	 * Get Original Servlet Path
	 * 
	 * @return String
	 */
	public String getOriginalServletPath() {
		return originalServletPath;
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