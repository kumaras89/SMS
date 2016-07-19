package com.sms.core.message;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public class SMSDetails {
    private String name;
    private String message;
    private String phoneNumber;

    public SMSDetails(final String name, final String message, final String phoneNumber) {
        this.name = name;
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
