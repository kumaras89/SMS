package com.sms.core.security.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.gson.Gson;
import com.sms.core.security.LoginRequest;

public class SmsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginRequest loginRequest = getLoginRequest(request);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword());
		setDetails(request, token);
		return getAuthenticationManager().authenticate(token);
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		BufferedReader reader = null;
		LoginRequest loginRequest = new LoginRequest();
		try {
			reader = request.getReader();
			Gson gson = new Gson();
			loginRequest = gson.fromJson(reader, LoginRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {

			}
		}
		return loginRequest;
	}
}
