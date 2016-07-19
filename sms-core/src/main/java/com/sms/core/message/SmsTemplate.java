package com.sms.core.message;

/**
 * Created by sathish on 7/16/2016.
 */
public class SmsTemplate
{
    private String  phoneNumber;
    private String message;

    public SmsTemplate(final String phoneNumber, final String message)
    {
        this.phoneNumber = phoneNumber;
        this.message= message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }
}
