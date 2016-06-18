package com.sms.core.common;

/**
 * Created by sathish on 6/18/2016.
 */
public class ErrorInfo
{
    private String fieldName;
    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public static Builder<ErrorInfo> builder(){
        return Builder.of(ErrorInfo.class);
    }
}
