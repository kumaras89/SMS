package com.sms.core.message;

import com.sms.core.common.Builder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sathish on 7/13/2016.
 */

@Entity
@Table(name = "SMS_TR_MESSAGING_SERVICE")
@SequenceGenerator(name = "SMS_SQ_MS",sequenceName = "SMS_SQ_MS")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_MS")
    private  int id;

    @Column(name = "MS_RECEIVER")
    private String toReceiver;

    @Column(name = "MS_SENDING_DATE")
    private Date sendingDate;

    @Column(name = "MS_MESSAGE")
    private String message;

    @Column(name = "MS_STATUS")
    private String stauts;

    public static Builder<Message> builder() {
        return Builder.of(Message.class);
    }

    public static Builder<Message> toBuilder(final MessageInfo messageInfo) {
        return builder()
                .with(Message::getId, messageInfo.getId())
                .on(Message::getToReceiver).set(messageInfo.getToReceiver())
                .on(Message::getSendingDate).set(new Date())
                .on(Message::getMessage).set(messageInfo.getMessage())
                .on(Message::getStauts).set(messageInfo.getStauts());
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

    public String getStauts() {
        return stauts;
    }
}
