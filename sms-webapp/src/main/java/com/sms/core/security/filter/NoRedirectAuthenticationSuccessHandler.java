package com.sms.core.security.filter;

import com.google.gson.Gson;
import com.sms.core.SmsSessionContext;
import com.sms.core.SmsSessionContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("customAuthenticationSuccessHandler")
public class NoRedirectAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoRedirectAuthenticationSuccessHandler.class);

    @Autowired
    private SmsSessionContextService smsSessionContextService;

    @Override
    public void onAuthenticationSuccess(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication) throws IOException, ServletException {

        try {
            //These info must be read from table and set into SMS context
            final SmsSessionContext context = smsSessionContextService.initSessionContext(authentication.getName());
            final Map<String, Object> jsonResult = new HashMap<>();
            jsonResult.put("result", true);
            jsonResult.put("message", "Successfully logged in!!");

            final Map<String, Object> userJson = new HashMap<>();
            userJson.put("markettingEmployee", context.getMarkettingEmployeeCode());
            userJson.put("firstName", context.getLoggedInUserInfo().getFirstName());
            userJson.put("lastName", context.getLoggedInUserInfo().getLastName());
            userJson.put("role", context.getLoggedInUserInfo().getRole());
            userJson.put("branch", context.getLoggedInUserInfo().getBranch());
            userJson.put("allowedOperations", context.getAllowedOperations());
            userJson.put("securedOperations", context.getSecuredOperations());
            jsonResult.put("user", userJson);

            LOGGER.debug("Authentication Successfully done");

            response.getWriter().print(new Gson().toJson(jsonResult));
            response.getWriter().flush();
        } catch (final IOException e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
