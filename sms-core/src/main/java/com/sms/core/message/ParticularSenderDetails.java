package com.sms.core.message;

import com.sms.core.student.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by sathish on 7/18/2016.
 */
@Component("Others")
public class ParticularSenderDetails implements SenderDetailsProvider
{
    @Autowired
    private MessageService messageService;


    @Override
    public List<SenderDetails> createDetails() {
      return  messageService.listAll().stream().map(this::getSenderDetails).collect(Collectors.toList());
    }
    private SenderDetails getSenderDetails(MessageInfo messageInfo) {
        return new SenderDetails("Unknown User",messageInfo.getToReceiver());
    }
}
