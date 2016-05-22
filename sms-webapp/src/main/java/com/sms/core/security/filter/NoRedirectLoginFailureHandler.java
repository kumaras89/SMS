package com.sms.core.security.filter;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class NoRedirectLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		try {
			Map<String, Object> jsonResult = new HashMap<>();
			jsonResult.put("result", false);
			jsonResult.put("message", "Login records doesn't matches in our details!!");

			response.getWriter().print(new Gson().toJson(jsonResult));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
