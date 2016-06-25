package com.sms.core.security.filter;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sms.core.common.Error;
import com.sms.core.common.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class NoRedirectLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		try {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			Error error = new Error();
			error.setErrorInfo(new ErrorInfo("", "Login records doesn't matches in our details!!"));
			response.getWriter().print(new Gson().toJson(error));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
