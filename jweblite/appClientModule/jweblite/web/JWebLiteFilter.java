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

import jweblite.web.application.JWebLiteApplication;
import jweblite.web.application.JWebLiteApplicationListener;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.dispatcher.ServletRequestDispatchSettings;
import jweblite.web.session.JWebLiteSessionFactory;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class JWebLiteFilter
 */
public class JWebLiteFilter implements Filter {

	private Log log = LogFactory.getLog(this.getClass());

	private JWebLiteApplication application = JWebLiteApplication.get();

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
		this.application.setFilterConfig(filterConfig);
		// init request dispatcher
		this.application.setRequestDispatcher(new JWebLiteRequestDispatcher(
				filterConfig.getUrlPathPadding()));
		// init class
		String initClassName = filterConfig.getInitClassName();
		if (initClassName != null) {
			try {
				Class initClass = Class.forName(initClassName);
				if (initClass != null) {
					this.application.setInitClass(initClass.newInstance());
				}
			} catch (Exception e) {
				log.warn("Init class failed!", e);
			}
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// trigger unbound event
		Object initClassInstance = null;
		if ((initClassInstance = this.application.getInitClass()) != null
				&& initClassInstance instanceof JWebLiteApplicationListener) {
			((JWebLiteApplicationListener) initClassInstance).destroy();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// filter config
		JWebLiteFilterConfig filterConfig = this.application.getFilterConfig();
		String attrPrefix = filterConfig.getAttrPrefix();
		// redirect by request dispatcher
		ServletRequestDispatchSettings requestDispatchSettings = (ServletRequestDispatchSettings) req
				.getAttribute(attrPrefix.concat("ReqDispatcherFoward"));
		if (requestDispatchSettings == null
				&& this.application.getRequestDispatcher() != null
				&& (requestDispatchSettings = this.application
						.getRequestDispatcher().doDispatch(req)) != null) {
			req.setAttribute(attrPrefix.concat("ReqDispatcherFoward"),
					requestDispatchSettings);
			req.getRequestDispatcher(
					requestDispatchSettings.getReferenceResourcePath())
					.forward(req, resp);
			return;
		}
		// starting
		JWebLiteRequestWrapper reqWrapper = new JWebLiteRequestWrapper(req,
				filterConfig.getEncoding());
		resp.setHeader("Implementation-Title", "jweblite");
		resp.setCharacterEncoding(filterConfig.getEncoding());
		// parse
		Class reqClass = null;
		try {
			reqClass = Class.forName(requestDispatchSettings
					.getReferenceClassName());
		} catch (Exception e) {
		}
		if (this.log.isInfoEnabled()) {
			this.log.info(String
					.format("RequestInfo [ ClientIP: %s, ReqUrl: %s, ReqParam: %s, ReqClass: %s ]",
							reqWrapper.getRemoteAddr(), reqWrapper
									.getRequestURI(), reqWrapper
									.getQueryString(),
							(reqClass != null ? reqClass.getName() : null)));
		}
		// init class
		boolean isIgnoreViewer = false;
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			try {
				JWebLitePage reqClassInstance = (JWebLitePage) reqClass
						.newInstance();
				isIgnoreViewer = reqClassInstance.doRequest(reqWrapper, resp);
				reqWrapper.setAttribute(attrPrefix, reqClassInstance);
				reqWrapper.setAttribute(attrPrefix.concat("Req"), reqWrapper);
				// session
				reqWrapper.getSession(true).setAttribute(
						attrPrefix.concat("SessionFactory"),
						JWebLiteSessionFactory.get());
			} catch (Throwable e) {
				throw new ServletException(e);
			}
		}
		// pass the request along the filter chain
		if (!isIgnoreViewer) {
			chain.doFilter(reqWrapper, resp);
		}
	}

}