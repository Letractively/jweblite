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
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.application.JWebLiteApplicationBoundListener;
import jweblite.web.session.JWebLiteSessionFactory;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class JWebLiteFilter
 */
public class JWebLiteFilter implements Filter {

	private Log log = LogFactory.getLog(this.getClass());

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
		// init filter config
		JWebLiteFilterConfig filterConfig = new JWebLiteFilterConfig(fConfig);

		JWebLiteApplication application = JWebLiteApplication.get();
		application.setFilterConfig(filterConfig);
		// init class
		String initClassName = filterConfig.getInitClassName();
		if (initClassName != null) {
			try {
				Class initClass = Class.forName(initClassName);
				if (initClass != null) {
					application.setInitClass(initClass.newInstance());
				}
			} catch (Exception e) {
				log.warn("Init class failed!", e);
			}
		}
		// trigger bound event
		Object initClassInstance = null;
		if ((initClassInstance = application.getInitClass()) != null
				&& initClassInstance instanceof JWebLiteApplicationBoundListener) {
			((JWebLiteApplicationBoundListener) initClassInstance).bound();
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// trigger unbound event
		Object initClassInstance = null;
		if ((initClassInstance = JWebLiteApplication.get().getInitClass()) != null
				&& initClassInstance instanceof JWebLiteApplicationBoundListener) {
			((JWebLiteApplicationBoundListener) initClassInstance).unbound();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// filter config
		JWebLiteFilterConfig filterConfig = JWebLiteApplication.get()
				.getFilterConfig();
		// starting
		JWebLiteRequestWrapper req = new JWebLiteRequestWrapper(
				(HttpServletRequest) request, filterConfig.getEncoding());
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Implementation-Title", "jweblite");
		resp.setCharacterEncoding(filterConfig.getEncoding());
		// parse
		Class reqClass = this.getReferenceClassByUrl(req.getServletPath(),
				filterConfig.getUrlPathPadding());
		if (this.log.isInfoEnabled()) {
			this.log.info(String
					.format("RequestInfo [ ClientIP: %s, ReqUrl: %s, ReqParam: %s, ReqClass: %s ]",
							req.getRemoteAddr(), req.getRequestURI(),
							req.getQueryString(),
							(reqClass != null ? reqClass.getName() : null)));
		}
		// init class
		boolean isIgnoreViewer = false;
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			try {
				JWebLitePage reqClassInstance = (JWebLitePage) reqClass
						.newInstance();
				isIgnoreViewer = reqClassInstance.doRequest(req, resp);
				req.setAttribute(filterConfig.getAttrPrefix(), reqClassInstance);
				req.setAttribute(filterConfig.getAttrPrefix().concat("Req"),
						req);
				// session
				req.getSession(true).setAttribute(
						filterConfig.getAttrPrefix().concat("SessionFactory"),
						JWebLiteSessionFactory.get());
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