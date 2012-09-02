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

	private static final Log _cat = LogFactory.getLog(JWebLiteFilter.class);

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
				_cat.warn("Init class failed!", e);
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
			// dispatcher
			String reqDispatcherFowardId = attrPrefix
					.concat("ReqDispatcherFoward");
			JWebLiteRequestDispatchSettings reqDispatchSettings = (JWebLiteRequestDispatchSettings) req
					.getAttribute(reqDispatcherFowardId);
			if (reqDispatchSettings == null) {
				JWebLiteRequestDispatcher reqDispatcher = application
						.getRequestDispatcher();
				String refResourcePath = null;
				if (reqDispatcher != null
						&& (reqDispatchSettings = reqDispatcher
								.getDispatchSettings(servletPath)) != null
						&& (refResourcePath = reqDispatchSettings
								.getReferenceResourcePath()) != null) {
					req.setAttribute(reqDispatcherFowardId, reqDispatchSettings);
					req.getRequestDispatcher(refResourcePath)
							.forward(req, resp);
					return;
				}
				// allow servlet path like '/'
			} else {
				req.removeAttribute(reqDispatcherFowardId);
			}
			if (_cat.isInfoEnabled()) {
				_cat.info(String
						.format("RequestInfo: { ClientIP: %s, ReqUri: %s, ReqParam: %s }",
								req.getRemoteAddr(), req.getRequestURI(),
								req.getQueryString()));
			}
			// starting
			String encoding = filterConfig.getEncoding();
			JWebLiteRequestWrapper reqWrapper = null;
			if (req instanceof JWebLiteRequestWrapper) {
				reqWrapper = (JWebLiteRequestWrapper) req;
			} else {
				reqWrapper = new JWebLiteRequestWrapper(req, encoding);
			}
			JWebLiteResponseWrapper respWrapper = null;
			if (resp instanceof JWebLiteResponseWrapper) {
				respWrapper = (JWebLiteResponseWrapper) resp;
			} else {
				respWrapper = new JWebLiteResponseWrapper(req, resp, encoding,
						filterConfig.isGZipEnabled());
			}
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
			// prepare default variables
			reqWrapper.setAttribute("ContextPath", reqWrapper.getContextPath());
			// init class
			boolean isIgnoreView = false;
			if (reqClass != null
					&& JWebLitePage.class.isAssignableFrom(reqClass)) {
				try {
					JWebLitePage reqClassInstance = (JWebLitePage) reqClass
							.newInstance();
					reqWrapper.setAttribute(attrPrefix, reqClassInstance);
					reqWrapper.setAttribute(attrPrefix.concat("Req"),
							reqWrapper);
					// session manager
					reqWrapper.getSession(true).setAttribute(
							attrPrefix.concat("SessionManager"),
							JWebLiteSessionManager.get());
					reqClassInstance.doRequest(reqWrapper, respWrapper);
				} catch (SkipException se) {
					isIgnoreView = true;
				} catch (Throwable e) {
					throw new ServletException(e);
				}
			}
			// pass the request along the filter chain
			if (!isIgnoreView) {
				// trigger doBeforeRender event
				application.doBeforeRender(reqWrapper, respWrapper);
				chain.doFilter(reqWrapper, respWrapper);
			}
			// trigger doAfterRequest event
			application.doAfterRequest(reqWrapper, respWrapper);
			// do finish
			respWrapper.doFinish();
		} catch (Throwable e) {
			_cat.warn("Do filter failed!", e);
			this.doErrorPage(filterConfig, req, resp, e);
		}
	}

	/**
	 * Do Error Page
	 * 
	 * @param filterConfig
	 *            JWebLiteFilterConfig
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param e
	 *            Throwable
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doErrorPage(JWebLiteFilterConfig filterConfig,
			HttpServletRequest req, HttpServletResponse resp, Throwable e)
			throws IOException, ServletException {
		String errorPage = filterConfig.getErrorPage();
		String attrPrefix = filterConfig.getAttrPrefix();
		if (errorPage != null && errorPage.length() > 0) {
			if (errorPage.equalsIgnoreCase("null")) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} else {
				String errorDispatcherFowardId = attrPrefix
						.concat("ExceptionDispatcherFoward");
				try {
					if (req.getAttribute(errorDispatcherFowardId) != null) {
						throw new ServletException();
					}
					req.setAttribute(errorDispatcherFowardId, true);
					req.setAttribute(attrPrefix.concat("Exception"), e);
					req.getRequestDispatcher(errorPage).forward(req, resp);
				} catch (Throwable e2) {
					_cat.warn("Forward error page failed!");
				}
			}
		} else {
			throw new ServletException(e);
		}
	}

}