package com.sms.core.security.filter;

import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NoRedirectAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		try {
			//These info must be read from table and set into SMS context
			Map<String, Object>  jsonResult = new HashMap<>();
			jsonResult.put("result", true);
			jsonResult.put("message", "Successfully logged in!!");
			Map<String, Object>  userJson = new HashMap<>();
			userJson.put("firstName", "Admin");
			userJson.put("lastName", "");
			userJson.put("role", "ADMIN");
			userJson.put("allowedOperations", Arrays.asList("home", "branch", "course", "scheme", "user", "schema"));
			jsonResult.put("user", userJson);

			response.getWriter().print(new Gson().toJson(jsonResult));
	        response.getWriter().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
