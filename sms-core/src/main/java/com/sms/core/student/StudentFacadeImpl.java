package com.sms.core.student;

import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.MarketingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Ram on 6/26/2016.
 * <p></p>
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

    @Autowired
    private StudentEnrollmentConfig seConfig;

    @Autowired
    private MarketingEmployeeRepository marketingEmployeeRepository;

    @Autowired
    private SMSSenderDetailsGenerator smsSenderDetailsGenerator;

    @Override
    public Optional<StudentInfo> save(final StudentInfo studentInfo) {
        final StudentInfo newStudentInfo = StudentEnrollmentService.save(studentInfo).with(seConfig);
        //Sending welcome message to student,parent,marketing employee
        sendWelcomeMessage(newStudentInfo);

        return Optional.of(newStudentInfo);
    }

    @Override
    public List<StudentInfo> findAll() {
        return StudentEnrollmentService.findAll().with(seConfig);
    }

    @Override
    public Void delete(final Long id) {
        return StudentEnrollmentService.findAll(id).with(seConfig.getStuRepo());
    }

    @Override
    public Optional<StudentInfo> findById(final Long id) {
        return StudentEnrollmentService.findById(id).with(seConfig.getStuRepo());
    }

    @Override
    public Optional<StudentInfo> findByCode(final String code) {
        return StudentEnrollmentService.findByCode(code).with(seConfig.getStuRepo());
    }

    @Override
    public Optional<StudentInfo> findByScholarship(final String applicationNumber) {
        return StudentEnrollmentService.findByStudentScholarship(applicationNumber).with(seConfig.getStudScholarServ());
    }

    @Override
    public List<StudentInfo> search(final StudentSearchCriteria studentSearchCriteria) {
        return StudentSearchService
            .search(studentSearchCriteria)
            .local(StudentEnrollmentConfig::getStuRepo)
            .with(seConfig);
    }

    @Override
    public Optional<StudentInfo> update(final long id, final StudentInfo studentInfo) {
        return null;
    }

    private void sendWelcomeMessage(final StudentInfo newStudentInfo) {
        final MarketingEmployee marketingEmployee =
            marketingEmployeeRepository.findByCodeIgnoreCase(newStudentInfo.getMarketingEmployeeCode());

        final BiFunction<String, String, Function<String, SendingDetails>> sendingDetailsCreator =
            (messageCode, message) -> phoneNumber ->
                SendingDetails.builder().on(SendingDetails::getMessageCode).set(messageCode)
                    .on(SendingDetails::getSenderMessage).set(message)
                    .on(SendingDetails::getSenderPhoneNumber).set(phoneNumber)
                    .build();

        final BiFunction<String, String, String> senderMessage =
            (who, code) -> new StringBuilder("Hi").append(who).append(",Application No:").append(code).toString();

        final Function<String, String> messageCreator = (message) -> senderMessage.apply(message, newStudentInfo
            .getApplicationNumber());

        final String message = messageCreator.apply(newStudentInfo.getName());
        final String marketingMessage = messageCreator.apply(
            String.join(marketingEmployee.getName(), ",Name:", newStudentInfo.getName()));

        final List<SendingDetails> sendingDetails = new ArrayList<>();
        sendingDetails.add(sendingDetailsCreator.apply("SMS_STD_STD", message).apply(newStudentInfo.getPhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_PRT_STD", message)
            .apply(newStudentInfo.getAlternatePhoneNumber()));
        sendingDetails.add(sendingDetailsCreator.apply("SMS_EMP_STD", marketingMessage)
            .apply(marketingEmployee.getPhoneNumber()));

        smsSenderDetailsGenerator.createSMSDetails(sendingDetails);
    }
}
