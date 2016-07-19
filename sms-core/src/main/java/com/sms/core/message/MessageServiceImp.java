package com.sms.core.message;

import com.sms.core.common.FList;
import com.sms.core.repositery.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by sathish on 7/12/2016.
 */
@Service
@Transactional
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    private Map<String, SenderDetailsProvider> senderDetailsProvider;

    @Autowired
    private MessageServiceProvider messageServiceProvider;

    @Autowired
    public MessageServiceImp(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMesaging(MessageInfo messageInfo) {
        messageRepository.saveAndFlush(Message.toBuilder(messageInfo).build());
    }

    @Override
    public void sendMessageToAll(MessageInfo messageInfo) {

        //saveMesaging(messageInfo);

        senderDetailsProvider.get(messageInfo.getToReceiver())
                .createDetails()
                .stream()
                .map(
                        senderDetails ->MessageTemplateCreator.createSmsTemplate(senderDetails, messageInfo.getMessage())
                )
                .forEach(messageServiceProvider::sendSms);
    }

    @Override
    public List<MessageInfo> listAll() {
        return FList.of(this.messageRepository.findAll()).map(MessageInfo::toBuilder).get();
    }
}
