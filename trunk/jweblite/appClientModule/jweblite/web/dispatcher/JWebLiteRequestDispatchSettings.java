package jweblite.web.dispatcher;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class JWebLiteRequestDispatchSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String originalServletPath;
	private final String originalRequestUri;
	private String referenceClassName;
	private String referenceResourcePath;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(HttpServletRequest req,
			String referenceClassName, String referenceResourcePath) {
		super();
		this.originalServletPath = req.getServletPath();
		this.originalRequestUri = req.getRequestURI();

		this.referenceClassName = referenceClassName;
		this.referenceResourcePath = referenceResourcePath;
	}

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatchSettings(HttpServletRequest req) {
		this(req, null, null);
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
	 * Get Original Request Uri
	 * 
	 * @return String
	 */
	public String getOriginalRequestUri() {
		return originalRequestUri;
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