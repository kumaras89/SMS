package com.sms.core.message;

import com.sms.core.common.Builder;


/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public class SMSLoginDetails {

    private String username;
    private String password;
    private String sender;
    private String hash;
    private String smsServer;

    public static Builder<SMSLoginDetails> builder() {
        return Builder.of(SMSLoginDetails.class);
    }

    public String getPassword() {
        return password;
    }

    public String getSender() {
        return sender;
    }

    public String getHash() {
        return hash;
    }

    public String getUsername() {
        return username;
    }

    public String getSmsServer() {
        return smsServer;
    }
}
