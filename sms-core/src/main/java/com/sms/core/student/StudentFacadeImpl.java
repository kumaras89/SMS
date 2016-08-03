package com.sms.core.student;
import com.sms.core.message.SMSConfig;
import com.sms.core.message.SMSDetails;
import com.sms.core.message.SMSSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ram on 6/26/2016.
 * <p></p>
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

    @Value("${JOINED_WELCOME_MESSAGE_FOR_STUDENT}")
    private String welcomeMessage;

    @Autowired
    private StudentEnrollmentConfig seConfig;

    @Autowired
    private SMSConfig smsConfig;

    @Override
    public Optional<StudentInfo> save(final StudentInfo studentInfo) {
        final StudentInfo newStudentInfo = StudentEnrollmentService.save(studentInfo).with(seConfig);
        SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set(studentInfo.getName())
                                              .on(SMSDetails::getPhoneNumber).set(studentInfo.getPhoneNumber())
                                              .on(SMSDetails::getMessage).set(welcomeMessage).build()).apply(smsConfig);
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
}
