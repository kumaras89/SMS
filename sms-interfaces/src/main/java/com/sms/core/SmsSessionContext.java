package com.sms.core;

import com.sms.core.admin.UserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesan on 22/05/16.
 */
public class SmsSessionContext implements Serializable {

    private boolean contextIntialized = false;
    private UserInfo loggedInUserInfo;
    private List<String> allowedOperations;
    private List<String> securedOperations;
    private List<String> allowedUrls;

    public boolean isContextIntialized() {
        return contextIntialized;
    }

    public void setContextIntialized(boolean contextIntialized) {
        this.contextIntialized = contextIntialized;
    }

    public List<String> getAllowedUrls() {
        return allowedUrls;
    }

    public void setAllowedUrls(List<String> allowedUrls) {
        this.allowedUrls = allowedUrls;
    }

    public List<String> getSecuredOperations() {
        return securedOperations;
    }

    public void setSecuredOperations(List<String> securedOperations) {
        this.securedOperations = securedOperations;
    }

    public UserInfo getLoggedInUserInfo() {
        return loggedInUserInfo;
    }

    public void setLoggedInUserInfo(UserInfo loggedInUserInfo) {
        this.loggedInUserInfo = loggedInUserInfo;
    }

    public List<String> getAllowedOperations() {
        return allowedOperations;
    }

    public void setAllowedOperations(List<String> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }
}
