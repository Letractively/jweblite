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
		String thisPrefix = fConfig.getInitParameter("AttrPrefix");
		if (thisPrefix != null && thisPrefix.length() > 0) {
			this.attrPrefix = thisPrefix;
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// parse
		Class reqClass = null;
		try {
			String reqUrl = req.getServletPath();
			reqClass = Class.forName(StringUtils
					.parseUrlPathToClassName(reqUrl));
		} catch (Exception e) {
		}
		// init
		if (reqClass != null
				&& JWebLiteInterface.class.isAssignableFrom(reqClass)) {
			try {
				JWebLiteInterface reqClassInstance = (JWebLiteInterface) reqClass
						.newInstance();
				reqClassInstance.doRequest(req, resp);
				request.setAttribute(this.attrPrefix, reqClassInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}