package com.sms.core.message;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
@Component
public class DefaultSenderDetails implements SenderDetailsProvider {
    @Override
    public Function<MessageConfig, List<SMSDetails>> senderDetails(final MessageInfo messageInfo) {
        return messageConfig -> {
            final List<SMSDetails> senderDetails = new ArrayList<>();
            senderDetails.add(Optional.ofNullable(messageInfo).map(mi -> SMSDetails.builder().on(SMSDetails::getName).set(messageInfo.getToReceiver())
                    .on(SMSDetails::getPhoneNumber).set(messageInfo.getPhoneNumber())
                    .on(SMSDetails::getMessage).set(messageInfo.getMessage()).build()).get());
            return senderDetails;
        };
    }

    @Override
    public String getType() {
        return "Others";
    }
}
