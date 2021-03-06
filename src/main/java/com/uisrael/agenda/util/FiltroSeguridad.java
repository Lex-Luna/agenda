package com.uisrael.agenda.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "filtroSeguridad", urlPatterns = { "*.xhtml" })
public class FiltroSeguridad implements Filter {

	public FiltroSeguridad() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/administracion/") < 0 || reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				res.sendRedirect(req.getContextPath() + "/login.xhtml");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}