package com.sms.core.security.filter;

import com.sms.core.SmsSessionContext;
import com.sms.core.SmsSessionContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("operationAuthorizationVoter")
public class OperationAuthorizationVoter implements AccessDecisionVoter<FilterInvocation> {

    @Autowired
    private SmsSessionContext sessionContext;

    @Autowired
    private AntPathMatcher matcher;

    @Autowired
    private SmsSessionContextService sessionContextService;


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> collection) {
        return Optional.of(fi.getRequestUrl())
                .filter(url -> sessionContextService.getSecuredUrls().stream().anyMatch(u -> matcher.match(u, url)))
                .filter(url -> !sessionContext.isContextIntialized() || !sessionContext.getAllowedUrls().stream().anyMatch(u -> matcher.match(u, url)))
                .map(url -> ACCESS_DENIED)
                .orElse(ACCESS_GRANTED);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }



}
