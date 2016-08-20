package com.sms.core.student;

import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.StudentScholarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    StudentScholarRepository studentScholarRepository;

    @Autowired
    SMSSenderDetailsGenerator sendToAllImp;

    @Override
    public Optional<StudentInfo> save(final StudentInfo studentInfo) {

        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentInfo.getMarketingEmployeeCode());

        StudentScholar studentScholar = studentScholarRepository.findByApplicationNumberIgnoreCase(studentInfo.getApplicationNumber());

        final StudentInfo newStudentInfo = StudentEnrollmentService.save(studentInfo).with(seConfig);

        List<SendingDetails> sendingDetailsList = new ArrayList<>();


        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(newStudentInfo.getPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(newStudentInfo.getName())
                                .append(",Application No:")
                                .append(newStudentInfo.getCode())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_STUDENT")
                .build());

        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(studentScholar.getParentPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(newStudentInfo.getName())
                                .append(",Application No:")
                                .append(newStudentInfo.getCode())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_STUDENT")
                .build());

        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(marketingEmployee.getPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(marketingEmployee.getName())
                                .append(",Name:")
                                .append(newStudentInfo.getName())
                                .append(",Application No:")
                                .append(newStudentInfo.getCode())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_MARKET_EMP")
                .build());

        sendToAllImp.createSMSDetails(sendingDetailsList);

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
}
