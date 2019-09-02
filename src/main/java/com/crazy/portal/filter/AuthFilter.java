package com.crazy.portal.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限过滤器
 * @author xin.xia
 * @date 2017-10-11
 */
public class AuthFilter implements Filter{

	String[] includeUrls = new String[]{"/login", "/static"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain)
									throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		Object isLoginObj = req.getSession().getAttribute("isLogin");
		Boolean isLogin = isLoginObj == null ? null : (Boolean) isLoginObj;
		String uri = req.getRequestURI();
		if((isLogin!= null && isLogin) || !isNeedFilter(uri)){
			filterChain.doFilter(request, response);
			return;
		}
		rep.sendRedirect(req.getContextPath()+"/login");
	}

	/**
	 * @Description: 是否需要过滤
	 */
	public boolean isNeedFilter(String uri) {
		for (String includeUrl : includeUrls) {
			if(includeUrl.equals(uri) || uri.contains(includeUrl)) {
				return false;
			}
		}
		return true;
	}
}
