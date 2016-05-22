package com.sms.core.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class NoRedirectLogoutSucessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		try {

			response.getWriter().print("{ \"result\": true, \"message\" : \"Logged out!\" }");
	        response.getWriter().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
