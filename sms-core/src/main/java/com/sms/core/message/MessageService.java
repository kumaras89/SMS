package com.sms.core.message;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.Promise;
import com.sms.core.common.Reader;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {


    private static MessageInfo messageToInfo(final Message source) {
        return MessageInfo.toBuilder(source).build();
    }

    public static Reader<MessageConfig, List<String>> sendMessage(final MessageInfo messageInfo) {
        return Reader.of( messageConfig -> Do.of(Message.toBuilder(messageInfo).build())
                                             .then(message -> messageConfig.getMessageRepository().save(message))
                                             .then(message -> messageConfig.getDetailsProviders().get(message.getToReceiver())
                                                                           .senderDetails(messageInfo).apply(messageConfig))
                                             .then(senderDetails ->
                                                 senderDetails.stream().map(
                                                     senderDetail ->
                                                         SMSSender.sendSms(senderDetail).apply(messageConfig)
                                                 ).collect(Collectors.toList())
                                             ).get());


    }

    public static Reader<MessageConfig,List<MessageInfo>> listAll() {
        return Reader.of( messageConfig ->  FList.of(messageConfig.getMessageRepository().findAll()).map(MessageService::messageToInfo).get());
    }
}
