package com.wangcai.common.authority;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.wangcai.member.base.vo.MemUser;

public class AuthSuccessHandler implements AuthenticationSuccessHandler,InitializingBean {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	public void afterPropertiesSet() throws Exception {
		
	}

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		MemUser user = (MemUser)authentication.getPrincipal();
		if (user != null && user.getHomepage() != null) {
			redirectStrategy.sendRedirect(request, response, user.getHomepage());
		}
	}
}
