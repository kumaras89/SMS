package com.sms.core.message;

import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sathish on 8/12/2016.
 */
@Entity
@Table(name = "SMS_WELCOME_MESSAGE")
public class WelcomeMessage {
    @Id
    private long id;

    @Column(name = "SMS_CODE")
    private String code;

    @Column(name = "SMS_MESSAGE")
    private String message;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static Builder<WelcomeMessage> builder() {
        return Builder.of(WelcomeMessage.class);
    }
}
