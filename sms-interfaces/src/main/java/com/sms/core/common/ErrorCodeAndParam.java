package com.sms.core.common;

/**
 * Created by Ganesan on 02/06/16.
 */
public class ErrorCodeAndParam {

    private final ErrorCode errorCode;
    private final String[] params;

    public ErrorCodeAndParam(ErrorCode errorCode, String... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String[] getParams() {
        return params;
    }
}
