package jweblite.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;

/**
 * Servlet Filter implementation class JWebLiteFilter
 */
public class JWebLiteFilter implements Filter {

	private String attrPrefix = "JWL";
	private String encoding = "UTF-8";

	/**
	 * Default constructor.
	 */
	public JWebLiteFilter() {
		super();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String attrPrefix = fConfig.getInitParameter("AttrPrefix");
		String encoding = fConfig.getInitParameter("Encoding");
		if (attrPrefix != null && attrPrefix.length() > 0) {
			this.attrPrefix = attrPrefix;
		}
		if (encoding != null && encoding.length() > 0) {
			this.encoding = encoding;
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		JWebLiteRequestWrapper req = new JWebLiteRequestWrapper(
				(HttpServletRequest) request, this.encoding);
		HttpServletResponse resp = (HttpServletResponse) response;
		// parse
		Class reqClass = this.getClassNameByUrl(req.getServletPath());
		// init
		boolean isSkipped = true;
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			try {
				JWebLitePage reqClassInstance = (JWebLitePage) reqClass
						.newInstance();
				isSkipped = reqClassInstance.doRequest(req, resp);
				req.setAttribute(this.attrPrefix, reqClassInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		// pass the request along the filter chain
		if (isSkipped) {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Get Class Name By Url
	 * 
	 * @param url
	 *            String
	 * @return Class
	 */
	public Class getClassNameByUrl(String url) {
		if (url == null) {
			return null;
		}
		Class result = null;
		try {
			// parse
			String reqClassName = StringUtils.parseUrlPathToClassName(url);
			if (reqClassName != null) {
				result = Class.forName(reqClassName);
			}
		} catch (Exception e) {
		}
		return result;
	}

}