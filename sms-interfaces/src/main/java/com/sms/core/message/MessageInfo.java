package com.sms.core.message;

import com.sms.core.common.Builder;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by sathish on 7/13/2016.
 */
public class MessageInfo
{
    private int id;

    @NotNull(message ="Receiver is Empty")
    private String toReceiver;

    private Date sendingDate;

    @NotNull(message = "Message is Empty")
    @Size(max = 279,min = 1,message = "Check your entered character it should be in between {min} to {max}")
    private String message;

    private String status;

    public static Builder<MessageInfo> builder() {
        return Builder.of(MessageInfo.class);
    }

    public static MessageInfo toBuilder(final Message message) {
        return builder()
                .with(MessageInfo::getId, message.getId())
                .on(MessageInfo::getToReceiver).set(message.getToReceiver())
                .on(MessageInfo::getMessage).set(message.getMessage())
                .on(MessageInfo::getSendingDate).set(message.getSendingDate())
                .on(MessageInfo::getStatus).set(message.getStatus().name()).build();
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
}
