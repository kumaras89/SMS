package com.sms.core.message;

import com.sms.core.common.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class MessageInfo implements Serializable {

    private static final long serialVersionUID = -69442450730074854L;
    private int id;

    @NotNull(message = "Receiver is Empty")
    private String toReceiver;

    private Date sendingDate;

    @NotNull(message = "Message is Empty")
    @Size(max = 279, min = 1, message = "Check your entered character it should be in between {min} to {max}")
    private String message;
    private String status;

    private String phoneNumber;

    public static Builder<MessageInfo> builder() {
        return Builder.of(MessageInfo.class);
    }

    public static Builder<MessageInfo> toBuilder(final Message theMessage) {
        return builder()
            .with(MessageInfo::getId, theMessage.getId())
            .on(MessageInfo::getToReceiver).set(theMessage.getToReceiver())
            .on(MessageInfo::getMessage).set(theMessage.getMessage())
            .on(MessageInfo::getSendingDate).set(theMessage.getSendingDate())
            .on(MessageInfo::getStatus).set(theMessage.getStauts().name())
            .on(MessageInfo::getPhoneNumber).set(theMessage.getPhoneNumber());
    }

    public int getId() {
        return id;
    }

    public String getToReceiver() {
        return toReceiver;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
