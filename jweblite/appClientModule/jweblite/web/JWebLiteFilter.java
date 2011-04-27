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
		Class reqClass = null;
		try {
			String reqClassName = StringUtils.parseUrlPathToClassName(req
					.getServletPath());
			if (reqClassName != null) {
				reqClass = Class.forName(reqClassName);
			}
		} catch (Exception e) {
		}
		// init
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			try {
				JWebLitePage reqClassInstance = (JWebLitePage) reqClass
						.newInstance();
				reqClassInstance.doRequest(req, resp);
				req.setAttribute(this.attrPrefix, reqClassInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

}