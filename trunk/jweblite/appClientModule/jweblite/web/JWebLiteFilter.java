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
import jweblite.web.dispatcher.JWebLiteRequestDispatchSettings;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.session.JWebLiteSessionManager;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

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
		// init application class
		String initClassName = filterConfig.getInitClassName();
		if (initClassName != null) {
			try {
				Class initClass = Class.forName(initClassName);
				if (initClass != null) {
					Object initClassInstance = initClass.newInstance();
					if (JWebLiteApplication.class.isAssignableFrom(initClass)) {
						JWebLiteApplication.application = (JWebLiteApplication) initClassInstance;
					}
				}
			} catch (Exception e) {
				log.warn("Init class failed!", e);
			}
		}
		// setter
		JWebLiteApplication application = JWebLiteApplication.get();
		application.setFilterConfig(filterConfig);
		// init request dispatcher
		application.setRequestDispatcher(new JWebLiteRequestDispatcher(
				filterConfig.getUrlPathPadding()));
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// trigger unbound event
		JWebLiteApplication.get().destroy();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// filter config
		JWebLiteApplication application = JWebLiteApplication.get();
		JWebLiteFilterConfig filterConfig = application.getFilterConfig();
		String attrPrefix = filterConfig.getAttrPrefix();
		try {
			// redirect by request dispatcher
			String servletPath = req.getServletPath();
			String reqDispatcherFowardId = attrPrefix
					.concat("ReqDispatcherFoward");
			// reqDispatchSettings could be null
			JWebLiteRequestDispatchSettings reqDispatchSettings = (JWebLiteRequestDispatchSettings) req
					.getAttribute(reqDispatcherFowardId);
			if (reqDispatchSettings == null) {
				JWebLiteRequestDispatcher reqDispatcher = application
						.getRequestDispatcher();
				String refResourcePath = null;
				if (reqDispatcher != null
						&& (reqDispatchSettings = reqDispatcher.doDispatch(req)) != null
						&& (refResourcePath = reqDispatchSettings
								.getReferenceResourcePath()) != null
						&& !refResourcePath.equalsIgnoreCase(servletPath)) {
					req.setAttribute(reqDispatcherFowardId, reqDispatchSettings);
					req.getRequestDispatcher(refResourcePath)
							.forward(req, resp);
					return;
				}
			} else {
				req.removeAttribute(reqDispatcherFowardId);
			}
			// starting
			String encoding = filterConfig.getEncoding();
			JWebLiteRequestWrapper reqWrapper = new JWebLiteRequestWrapper(req,
					encoding);
			JWebLiteResponseWrapper respWrapper = new JWebLiteResponseWrapper(
					req, resp, encoding, filterConfig.isGZipEnabled());
			// trigger doBeforeRequest event
			application.doBeforeRequest(reqWrapper, respWrapper,
					reqDispatchSettings);
			// parse
			Class reqClass = null;
			String refClassName = null;
			if (reqDispatchSettings != null
					&& (refClassName = reqDispatchSettings
							.getReferenceClassName()) != null) {
				try {
					reqClass = Class.forName(refClassName);
				} catch (Exception e) {
				}
			}
			if (this.log.isInfoEnabled()) {
				this.log.info(String
						.format("RequestInfo [ ClientIP: %s, OriReqUri: %s, OriServletPath: %s, ReqServletPath: %s, ReqParam: %s, ReqClass: %s ]",
								reqWrapper.getRemoteAddr(),
								(reqDispatchSettings != null ? reqDispatchSettings
										.getOriginalRequestUri() : reqWrapper
										.getRequestURI()),
								(reqDispatchSettings != null ? reqDispatchSettings
										.getOriginalServletPath() : reqWrapper
										.getServletPath()), reqWrapper
										.getServletPath(), reqWrapper
										.getQueryString(),
								(reqClass != null ? reqClass.getName() : null)));
			}
			// init class
			boolean isIgnoreView = false;
			if (reqClass != null
					&& JWebLitePage.class.isAssignableFrom(reqClass)) {
				try {
					JWebLitePage reqClassInstance = (JWebLitePage) reqClass
							.newInstance();
					isIgnoreView = reqClassInstance.doRequest(reqWrapper,
							respWrapper);
					reqWrapper.setAttribute(attrPrefix, reqClassInstance);
					reqWrapper.setAttribute(attrPrefix.concat("Req"),
							reqWrapper);
					// session manager
					reqWrapper.getSession(true).setAttribute(
							attrPrefix.concat("SessionManager"),
							JWebLiteSessionManager.get());
				} catch (Throwable e) {
					throw new ServletException(e);
				}
			}
			// trigger doAfterRequest event
			application.doBeforeRender(reqWrapper, respWrapper);
			// pass the request along the filter chain
			if (!isIgnoreView) {
				chain.doFilter(reqWrapper, respWrapper);
			}
			// trigger doAfterRequest event
			application.doAfterRequest(reqWrapper, respWrapper);
			// do finish
			respWrapper.doFinish();
		} catch (Throwable e) {
			log.warn("Do filter failed!", e);
			String errorPage = filterConfig.getErrorPage();
			if (errorPage != null) {
				if (errorPage.equalsIgnoreCase("null")) {
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				} else {
					String errorDispatcherFowardId = attrPrefix
							.concat("ExceptionDispatcherFoward");
					try {
						if (req.getAttribute(errorDispatcherFowardId) != null) {
							throw new Exception();
						}
						req.setAttribute(errorDispatcherFowardId, true);
						req.setAttribute(attrPrefix.concat("Exception"), e);
						req.getRequestDispatcher(errorPage).forward(req, resp);
					} catch (Throwable e2) {
						log.warn("Forward error page failed!");
					}
				}
			} else {
				throw new ServletException(e);
			}
		}
	}

}