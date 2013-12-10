package org.fourgeeks.gha.webclient.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that verifies that the user is logged in
 * 
 * @author caparicio
 * 
 */
public class MemberLoginFilter implements Filter {

	private ServletContext servletContext;

	public void init(FilterConfig filterConfig) {
		servletContext = filterConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!isAuth(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Debe iniciar sesión para realizar esta acción");
			return; // break filter chain, requested JSP/servlet will not be
					// executed
		}

		// propagate to next element in the filter chain, ultimately JSP/
		// servlet gets executed
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	/**
	 * logic to accept or reject access to the page, check log in status
	 * 
	 * @return true when authentication is deemed valid
	 */
	protected boolean isAuth(HttpServletRequest req) {
		return req.getSession().getAttribute("user") != null;
	}
}