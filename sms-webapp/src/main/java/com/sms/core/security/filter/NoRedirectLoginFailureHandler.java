package com.sms.core.security.filter;


import com.google.gson.Gson;
import com.sms.core.common.Error;
import com.sms.core.common.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoRedirectLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoRedirectLoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException exception) throws IOException, ServletException {

        try {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            final Error error = new Error();
            error.setErrorInfo(new ErrorInfo("", "Login records doesn't matches in our details!!"));
            response.getWriter().print(new Gson().toJson(error));
            response.getWriter().flush();
        } catch (final IOException e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
