package com.sms.core.message;

import com.sms.core.common.Builder;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public class SMSDetails {
    private String name;
    private String message;
    private String phoneNumber;


    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Builder<SMSDetails> builder(){
        return Builder.of(SMSDetails.class);
    }
}
