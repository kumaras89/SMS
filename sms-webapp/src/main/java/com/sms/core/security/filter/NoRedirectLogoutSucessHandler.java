package com.sms.core.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoRedirectLogoutSucessHandler implements LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoRedirectLogoutSucessHandler.class);

    @Override
    public void onLogoutSuccess(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication)
        throws IOException, ServletException {

        try {
            response.getWriter().print("{ \"result\": true, \"message\" : \"Logged out!\" }");
            response.getWriter().flush();
        } catch (final IOException e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
