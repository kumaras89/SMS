package com.sms.core;

import com.fasterxml.jackson.databind.util.EnumValues;
import com.sms.core.common.DateUtils;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.MarketingEmployeeRepository;
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
 */
@Component
@Transactional
@EnableScheduling
public class StudentScholarJob {

    @Autowired
    private StudentScholarRepository studentScholarRepository;

    @Autowired
    private SMSSenderDetailsGenerator smsDetailsGenerator;

    @Autowired
    private MarketingEmployeeRepository marketingEmployeeRepository;

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

        final BiFunction<String, String, Function<String, SendingDetails>> sendingDetailsCreator =
                (messageCode, message) -> phoneNumber ->
                        SendingDetails.builder().on(SendingDetails::getMessageCode).set(messageCode)
                                .on(SendingDetails::getSenderMessage).set(message)
                                .on(SendingDetails::getSenderPhoneNumber).set(phoneNumber)
                                .build();

        //TODO Sathish add meesage code and message
        final List<SendingDetails> sendingDetails = new ArrayList<>();

        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentScholar.getMarketingEmployee().getCode());

        String marketingEmployeeMessage = new StringBuilder("Hi")
                .append(marketingEmployee.getName())
                .append(",Name:")
                .append(studentScholar.getName())
                .append(",Application No:")
                .append(studentScholar.getApplicationNumber())
                .toString();
        String studentMessage = new StringBuilder("Hi")
                .append(studentScholar.getName())
                .append(",Application No:")
                .append(studentScholar.getApplicationNumber())
                .toString();
        String parentMessage = new StringBuilder("Hi")
                .append(studentScholar.getName())
                .append(",Application No:")
                .append(studentScholar.getApplicationNumber())
                .toString();

        sendingDetails.add(sendingDetailsCreator.apply("SMS_STD_EXP", studentMessage).apply(studentScholar.getStudentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_PRT_EXP", parentMessage).apply(studentScholar.getParentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_EMP_EXP", marketingEmployeeMessage).apply(studentScholar.getMarketingEmployee().getPhoneNumber()));

        smsDetailsGenerator.createSMSDetails(sendingDetails);
    }
}
