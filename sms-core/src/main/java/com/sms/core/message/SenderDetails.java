package com.sms.core.message;

/**
 * Created by sathish on 7/12/2016.
 */
public class SenderDetails
{
    private String name;
    private String phoneNumber;

    public SenderDetails(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
