package com.sms.core.message;

import com.sms.core.common.Builder;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public class SMSLoginDetails {

    private String user;
    private String password;
    private String senderId;
    private String priority;
    private String smstype;
    private String smsServer;

    public static Builder<SMSLoginDetails> builder() {
        return Builder.of(SMSLoginDetails.class);
    }

    public String getPassword() {
        return password;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getPriority() {
        return priority;
    }

    public String getSmstype() {
        return smstype;
    }

    public String getSmsServer() {
        return smsServer;
    }

    public String getUser() {
        return user;
    }
}
