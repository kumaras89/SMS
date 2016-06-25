package com.sms.core.common;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sathish on 6/18/2016.
 */
public class Error
{
    private List<ErrorInfo> errorInfo;

    public List<ErrorInfo> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<ErrorInfo> errorInfo) {
        this.errorInfo = errorInfo;
    }
    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = Arrays.asList(errorInfo);
    }
}
