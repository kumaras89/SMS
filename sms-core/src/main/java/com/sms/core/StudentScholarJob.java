package com.sms.core;

import com.sms.core.common.DateUtils;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.scholarship.ScholarStatus;
import com.sms.core.student.StudentScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Ganesan on 20/08/16.
 * <p></p>
 */
@Component
@Transactional
@EnableScheduling
public class StudentScholarJob {

    @Autowired
    private StudentScholarRepository studentScholarRepository;

    @Autowired
    private SMSSenderDetailsGenerator smsDetailsGenerator;

    @Scheduled(cron = "0 0 23 * * *")
    public void checkStudentScholarStatus() {
        studentScholarRepository
            .findAll((root, query, cb) -> cb.equal(root.<String>get("status"), ScholarStatus.INSERTED))
            .stream()
            .filter(studentScholar -> DateUtils.isOneDay(studentScholar.getCreatedDate()))
            .forEach(studentScholar -> {
                    updateStatus(studentScholar);
                    sendSMS(studentScholar);
                }
            );
    }

    public void updateStatus(final StudentScholar studentScholar) {
        studentScholarRepository.updateStatus(ScholarStatus.EXPIRED, studentScholar.getApplicationNumber());
    }

    public void sendSMS(final StudentScholar studentScholar) {
        final MarketingEmployee marketingEmployee = studentScholar.getMarketingEmployee();

        final BiFunction<String, String, String> senderMessage =
            (who, code) -> new StringBuilder("Hi").append(who).append(",Application No:").append(code).toString();

        final String welcomeMessage = senderMessage.apply(
            studentScholar.getName(),
            studentScholar.getApplicationNumber());

        final String marketingMessage =
            senderMessage.apply(
                String.join(marketingEmployee.getName(), ",Name:", studentScholar.getName()),
                studentScholar.getApplicationNumber());

        final BiFunction<String, String, Function<String, SendingDetails>> sendingDetailsCreator =
            (messageCode, message) -> phoneNumber ->
                SendingDetails.builder().on(SendingDetails::getMessageCode).set(messageCode)
                    .on(SendingDetails::getSenderMessage).set(message)
                    .on(SendingDetails::getSenderPhoneNumber).set(phoneNumber)
                    .build();

        final List<SendingDetails> sendingDetails = new ArrayList<>();

        sendingDetails.add(sendingDetailsCreator.apply("SMS_STD_EXP", welcomeMessage)
            .apply(studentScholar.getStudentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_PRT_EXP", welcomeMessage)
            .apply(studentScholar.getParentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_EMP_EXP", marketingMessage)
            .apply(marketingEmployee.getPhoneNumber()));

        smsDetailsGenerator.createSMSDetails(sendingDetails);
    }
}
