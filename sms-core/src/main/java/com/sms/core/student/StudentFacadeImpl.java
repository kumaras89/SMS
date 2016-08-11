package com.sms.core.student;
import com.sms.core.SmsException;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.*;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.MessageRepository;
import com.sms.core.repositery.StudentRepository;
import com.sms.core.repositery.StudentScholarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Autowired
    private StudentEnrollmentConfig seConfig;

    @Autowired
    private MarketingEmployeeRepository marketingEmployeeRepository;

    @Autowired
    StudentScholarRepository studentScholarRepository;

    @Autowired
    SendToAllImp sendToAllImp;

    private SendingDetails SetAllDataForSendingDetails(StudentInfo studentInfo)
    {
        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentInfo.getMarketingEmployeeCode());
        StudentScholar studentScholarInfo = studentScholarRepository.findByApplicationNumberIgnoreCase(studentInfo.getApplicationNumber());

        return SendingDetails.builder().on(SendingDetails::getGuardianPhoneNumber).set(studentScholarInfo.getParentPhoneNumber())
                .on(SendingDetails::getStudentPhoneNumber).set(studentInfo.getPhoneNumber())
                .on(SendingDetails::getStudentApplicationNumber).set(studentInfo.getCode())
                .on(SendingDetails::getStudentName).set(studentInfo.getName())
                .on(SendingDetails::getMarketingEmployeeName).set(marketingEmployee.getName())
                .on(SendingDetails::getMarketingEmployeePhonenumber).set(marketingEmployee.getPhoneNumber())
                .build();
    }

    @Override
    public Optional<StudentInfo> save(final StudentInfo studentInfo) {

        final StudentInfo newStudentInfo = StudentEnrollmentService.save(studentInfo).with(seConfig);
        sendToAllImp.sendAll(SetAllDataForSendingDetails(studentInfo));
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
    public Optional<StudentInfo> update(final long id,final StudentInfo studentInfo)
    {
        return Optional.ofNullable(StudentEnrollmentService.findById(id).with(seConfig.getStuRepo()))
                .map(student -> this.save(studentInfo))
                .orElseThrow(
                () ->  new SmsException("Student Update Error", "What you trying to do Update its not available"));
    }
}
