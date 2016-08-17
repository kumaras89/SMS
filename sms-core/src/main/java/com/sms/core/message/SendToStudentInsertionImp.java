package com.sms.core.message;

import com.sms.core.repositery.MessageRepository;
import com.sms.core.repositery.WelComeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sathish on 8/11/2016.
 */

@Component
public class SendToStudentInsertionImp implements SendMessageToAll {

    @Autowired
    private WelComeMessageRepository welComeMessageRepository;

    @Autowired
    private SMSConfig smsConfig;

    @Override
    public void sendAll(List<SendingDetails> sendingDetails) {
        for (SendingDetails sendDetails : sendingDetails) {
            WelcomeMessage welcomeMessage = welComeMessageRepository.findByCode(sendDetails.getMessageCode());
            SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set("--")
                    .on(SMSDetails::getPhoneNumber).set(sendDetails.getSenderPhoneNumber())
                    .on(SMSDetails::getMessage)
                    .set(new StringBuilder(sendDetails.getSenderMessage()).append(welcomeMessage.getMessage()).toString()).build()).apply(smsConfig);
        }

    }
}
