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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class JWebLiteFilter
 */
public class JWebLiteFilter implements Filter {

	private Log log = LogFactory.getLog(this.getClass());

	private String attrPrefix = "Jwl";
	private String encoding = "UTF-8";
	private int urlPathPadding = 0;

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
		String urlPathPadding = fConfig.getInitParameter("UrlPathPadding");
		if (attrPrefix != null && attrPrefix.length() > 0) {
			this.attrPrefix = attrPrefix;
		}
		if (encoding != null && encoding.length() > 0) {
			this.encoding = encoding;
		}
		if (urlPathPadding != null && urlPathPadding.length() > 0) {
			try {
				if ((this.urlPathPadding = Integer.parseInt(urlPathPadding)) < 0) {
					this.urlPathPadding = 0;
				}
			} catch (Exception e) {
			}
		}
		if (this.log.isInfoEnabled()) {
			this.log.info(String.format(
					"[ AttrPrefix: %s, Encoding: %s, UrlPathPadding: %s ]",
					attrPrefix, encoding, urlPathPadding));
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
		Class reqClass = this.getReferenceClassByUrl(req.getServletPath(),
				this.urlPathPadding);
		if (this.log.isInfoEnabled()) {
			this.log.info(String.format(
					"[ ClientIP: %s, ReqUrl: %s, ReqParam: %s, ReqClass: %s ]",
					req.getRemoteAddr(), req.getRequestURI(),
					req.getQueryString(),
					(reqClass != null ? reqClass.getName() : null)));
		}
		// init
		boolean isIgnoreViewer = false;
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			try {
				JWebLitePage reqClassInstance = (JWebLitePage) reqClass
						.newInstance();
				isIgnoreViewer = reqClassInstance.doRequest(req, resp);
				req.setAttribute(this.attrPrefix, reqClassInstance);
				req.setAttribute(this.attrPrefix + "Req", req);
			} catch (Throwable e) {
				throw new ServletException(e);
			}
		}
		// pass the request along the filter chain
		if (!isIgnoreViewer) {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Get Reference Class By Url
	 * 
	 * @param url
	 *            String
	 * @return Class
	 */
	public Class getReferenceClassByUrl(String url, int urlPathPadding) {
		if (url == null) {
			return null;
		}
		Class result = null;
		try {
			// parse
			String reqClassName = StringUtils.parseUrlPathToClassName(url,
					urlPathPadding);
			if (reqClassName != null) {
				result = Class.forName(reqClassName);
			}
		} catch (Exception e) {
		}
		return result;
	}

}