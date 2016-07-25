package com.sms.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rmurugaian on 7/25/2016.
 * <p></p>
 */
@Component
public class SMSConfig {

    @Autowired
    private SMSLoginDetails smsLoginDetails;


    public SMSLoginDetails getSmsLoginDetails() {
        return smsLoginDetails;
    }
}
