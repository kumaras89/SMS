package com.sms.core;

import com.sms.core.common.DateUtils;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.scholarship.ScholarStatus;
import com.sms.core.student.StudentScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Ganesan on 20/08/16.
 */
@Component
public class StudentScholarJob {

    @Autowired
    private StudentScholarRepository studentScholarRepository;

    @Autowired
    private SMSSenderDetailsGenerator smsDetailsGenerator;

    @Scheduled(cron = "0 23 * * *")
    public void checkStudentScholarStatus() {

        studentScholarRepository
                .findAll((root, query, cb) -> cb.equal(root.<String>get("status"), ScholarStatus.INSERTED.name()))
                .stream()
                .filter(studentScholar -> DateUtils.isOneDay(studentScholar.getCreatedDate()))
                .forEach(studentScholar -> {
                            updateStatus(studentScholar);
                            sendSMS(studentScholar);
                        }
                );
    }

    public void updateStatus(final StudentScholar studentScholar) {
        studentScholarRepository.updateStatus(ScholarStatus.EXPIRED.name(), studentScholar.getApplicationNumber());
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
        sendingDetails.add(sendingDetailsCreator.apply("", "").apply(studentScholar.getStudentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("", "").apply(studentScholar.getParentPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("", "").apply(studentScholar.getMarketingEmployee().getPhoneNumber()));

        smsDetailsGenerator.createSMSDetails(sendingDetails);
    }
}
