package jweblite.web.dispatcher;

import java.io.Serializable;

public class JWebLiteRequestDispatchSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String oriServletPath;
	private String refClassName;
	private String refResourcePath;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(String oriServletPath,
			String refClassName, String refResourcePath) {
		super();
		this.oriServletPath = oriServletPath;
		this.refClassName = refClassName;
		this.refResourcePath = refResourcePath;
	}

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(String oriServletPath) {
		this(oriServletPath, null, null);
	}

	@Override
	public String toString() {
		return String
				.format("{ OriServletPath: %s, RefResourcePath: %s, RefClassName: %s }",
						this.oriServletPath, this.refResourcePath,
						this.refClassName);
	}

	/**
	 * Get Original Servlet Path
	 * 
	 * @return String
	 */
	public String getOriginalServletPath() {
		return oriServletPath;
	}

	/**
	 * Get Reference Class Name
	 * 
	 * @return String
	 */
	public String getReferenceClassName() {
		return refClassName;
	}

	/**
	 * Set Reference Class Name
	 * 
	 * @param refClassName
	 *            String
	 */
	public void setReferenceClassName(String refClassName) {
		this.refClassName = refClassName;
	}

	/**
	 * Get Reference Resource Path
	 * 
	 * @return String
	 */
	public String getReferenceResourcePath() {
		return refResourcePath;
	}

	/**
	 * Set Reference Resource Path
	 * 
	 * @param refResourcePath
	 *            String
	 */
	public void setReferenceResourcePath(String refResourcePath) {
		this.refResourcePath = refResourcePath;
	}

}