package com.sms.core.message;

import com.sms.core.common.Builder;
import com.sun.javafx.scene.layout.region.StrokeBorderPaintConverter;

/**
 * Created by sathish on 8/11/2016.
 */
public class SendingDetails {

    private String senderPhoneNumber;
    private String senderMessage;
    private String messageCode;

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public String getMessageCode() { return messageCode; }

    public static Builder<SendingDetails> builder() {
        return Builder.of(SendingDetails.class);
    }
}
