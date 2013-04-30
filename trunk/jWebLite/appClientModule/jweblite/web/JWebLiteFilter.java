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

import jweblite.util.LogUtils;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;
import jweblite.web.session.JWebLiteSessionManager;
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
						JWebLiteApplication
								.set((JWebLiteApplication) initClassInstance);
					}
				}
			} catch (Exception e) {
				_cat.warn("Init class failed!", e);
				throw new ServletException(e);
			}
		}
		// setter
		JWebLiteApplication application = JWebLiteApplication.get();
		application.setFilterConfig(filterConfig);
		// init request dispatcher
		application.setRequestDispatcher(new JWebLiteRequestDispatcher());
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
		if (_cat.isInfoEnabled()) {
			_cat.info(LogUtils.formatDebugLog("RequestInfo",
					"ClientIP=%s, ReqUri=%s, ReqParam=%s", req.getRemoteAddr(),
					req.getRequestURI(), req.getQueryString()));
		}
		String encoding = filterConfig.getEncoding();
		String attrPrefix = filterConfig.getAttrPrefix();
		// wrap response
		JWebLiteResponseWrapper respWrapper = null;
		if (resp instanceof JWebLiteResponseWrapper) {
			respWrapper = (JWebLiteResponseWrapper) resp;
		} else {
			respWrapper = new JWebLiteResponseWrapper(req, resp, encoding,
					filterConfig.isGZipEnabled());
		}
		// prepare default variables
		req.setAttribute(attrPrefix.concat("CP"), req.getContextPath());
		// session manager
		req.getSession(true).setAttribute(attrPrefix.concat("SessionManager"),
				JWebLiteSessionManager.get());
		try {
			// parse form model
			String formModelAttrName = attrPrefix.concat("FM");
			FormModel formModel = (FormModel) req
					.getAttribute(formModelAttrName);
			if (formModel == null) {
				formModel = new FormModel(req, encoding);
			}
			req.setAttribute(formModelAttrName, formModel);
			// starting
			boolean isIgnoreView = false;
			try {
				// trigger doBeforeRequest event
				application.doBeforeRequest(req, respWrapper, formModel);
				// init class
				doRequest(req, respWrapper, formModel);
			} catch (SkipException se) {
				isIgnoreView = true;
			}
			// pass the request along the filter chain
			if (!isIgnoreView) {
				// trigger doBeforeRender event
				application.doBeforeRender(req, respWrapper, formModel);
				chain.doFilter(req, respWrapper);
			}
			// trigger doAfterRequest event
			application.doAfterRequest(req, respWrapper, formModel);
			// do finish
			respWrapper.doFinish();
		} catch (Throwable e) {
			_cat.warn("Do filter failed!", e);
			application.doError(req, respWrapper, e);
			this.doErrorPage(filterConfig, req, respWrapper, e);
		}
	}

	/**
	 * Do Request
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param respWrapper
	 *            JWebLiteResponseWrapper
	 * @param formModel
	 *            FormModel
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SkipException
	 */
	public void doRequest(HttpServletRequest req,
			JWebLiteResponseWrapper respWrapper, FormModel formModel)
			throws InstantiationException, IllegalAccessException,
			SkipException {
		JWebLiteApplication application = JWebLiteApplication.get();
		JWebLiteFilterConfig filterConfig = application.getFilterConfig();
		String attrPrefix = filterConfig.getAttrPrefix();
		JWebLiteRequestDispatcher reqDispatcher = application
				.getRequestDispatcher();
		Class reqClass = null;
		String refClassName = null;
		if (reqDispatcher != null
				&& (refClassName = reqDispatcher.dispatch(req.getServletPath())) != null) {
			try {
				reqClass = Class.forName(refClassName);
			} catch (Exception e) {
			}
		}
		if (reqClass != null && JWebLitePage.class.isAssignableFrom(reqClass)) {
			if (_cat.isInfoEnabled()) {
				_cat.info(LogUtils.formatDebugLog("DispatchInfo",
						"ClientIP=%s, ReqUri=%s, refClassName=%s",
						req.getRemoteAddr(), req.getRequestURI(), refClassName));
			}
			JWebLitePage reqClassInstance = (JWebLitePage) reqClass
					.newInstance();
			req.setAttribute(attrPrefix, reqClassInstance);
			reqClassInstance.doRequest(new WebContext(req, respWrapper),
					formModel);
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
		if (e == null || errorPage == null || errorPage.length() <= 0) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		if (errorPage.equalsIgnoreCase("debug")) {
			throw new ServletException(e);
		}
		String attrPrefix = filterConfig.getAttrPrefix();
		String exceptionAttrName = attrPrefix.concat("Exception");
		try {
			if (req.getAttribute(exceptionAttrName) != null) {
				throw new ServletException();
			}
			req.setAttribute(exceptionAttrName, e);
			req.getRequestDispatcher(errorPage).forward(req, resp);
		} catch (Throwable e2) {
			_cat.warn("Forward error page failed!");
		}
	}

}