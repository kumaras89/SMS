package com.sms.core.message;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
@Component
public class ScholarStudentSenderDetails implements SenderDetailsProvider {
    @Override
    public Function<MessageConfig, List<SMSDetails>> senderDetails(final MessageInfo messageInfo) {
        return messageConfig -> messageConfig.getStudentScholarService().findAll().stream().map(studentScholarInfo ->
            new SMSDetails(
                studentScholarInfo.getName(),
                studentScholarInfo.getStudentPhoneNumber(),
                messageInfo.getMessage()))
            .collect(Collectors.toList());
    }

    @Override
    public String getType() {
        return "Scholarship Students";
    }
}
