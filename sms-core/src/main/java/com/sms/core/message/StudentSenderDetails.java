package com.sms.core.message;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentSenderDetails implements SenderDetailsProvider {


    @Override
    public Function<MessageConfig, List<SMSDetails>> senderDetails(final MessageInfo messageInfo) {
        return messageConfig -> messageConfig.getStudentFacade().findAll().stream().map(
            studentInfo -> SMSDetails.builder().on(SMSDetails::getName).set(studentInfo.getName())
                    .on(SMSDetails::getPhoneNumber).set(studentInfo.getPhoneNumber())
                    .on(SMSDetails::getMessage).set(messageInfo.getMessage()).build()
        ).collect(Collectors.toList());
    }

    @Override
    public String getType() {
        return "Enrolled Students";
    }
}
