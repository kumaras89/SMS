package com.sms.core.scholarship;

import com.sms.core.SmsException;
import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.*;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.MessageRepository;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.student.Student;
import com.sms.core.student.StudentScholar;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentScholarServiceImpl implements StudentScholarService {


    private final StudentScholarRepository studentScholarRepository;
    private final MarketingEmployeeRepository marketingEmployeeRepository;
    private final BranchRepository branchRepository;
    private final SMSConfig smsConfig;
    private final MessageRepository messageRepository;

    /*
        @Value("${JOINED_WELCOME_MESSAGE_FOR_SCHOLARSHIP}")
        private String welcomeMessage;
    */

    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository,
                                     final MarketingEmployeeRepository marketingEmployeeRepository ,
                                     final BranchRepository branchRepository,
                                     final SMSConfig smsConfig,
                                     final MessageRepository messageRepository)
    {
        this.studentScholarRepository = studentScholarRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.branchRepository = branchRepository;
        this.smsConfig = smsConfig;
        this.messageRepository = messageRepository;
    }


    private static StudentScholarInfo scholarToInfo(final StudentScholar source) {
        return StudentScholarInfo.toBuilder(source).build();
    }

    @Override
    public List<StudentScholarInfo> findAll() {
        return FList.of(this.studentScholarRepository.findAll()).map(StudentScholarServiceImpl::scholarToInfo).get();
    }

    @Override
    public Optional<StudentScholarInfo> findByApplicationNumber(final String applicationNumber) {
        return Optional.of(this.studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber))
            .map(StudentScholarServiceImpl::scholarToInfo);
    }

    @Override
    public Optional<StudentScholarInfo> enrollStudent(final String applicationNumber) {
        final StudentScholar studentScholar = this.studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber);
        final StudentScholar studentScholarModified = studentScholarRepository.saveAndFlush(Builder.of(StudentScholar.class, studentScholar)
                .on(StudentScholar::getStatus).set(ScholarStatus.ENROLLED)
                .on(StudentScholar::getLastModifiedDate).set(new Date()).build());
        return Optional.of(studentScholarModified)
                .map(StudentScholarServiceImpl::scholarToInfo);
    }

    public Optional<StudentScholarInfo> commonSave(final StudentScholarInfo entityType)
    {
        final StudentScholar scholar = StudentScholar.toBuilder(entityType)
                .on(StudentScholar::getMarketingEmployee).set(marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode()))
                .on(StudentScholar::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .build();

        final Optional<StudentScholarInfo> newStudent = Optional.of(studentScholarRepository
                .saveAndFlush(scholar))
                .map(StudentScholarServiceImpl::scholarToInfo);

        return newStudent;
    }

    @Override
    public Optional<StudentScholarInfo> save(final StudentScholarInfo entityType) {

        Optional.ofNullable(studentScholarRepository
                .findByApplicationNumberIgnoreCase(entityType.getApplicationNumber()))
                .ifPresent( studentScholar -> new SmsException("applicationNumber", String
                        .format("Scholarship already exist for %s application number", entityType.getApplicationNumber())));

        Optional<StudentScholarInfo> studentScholarInfo = this.commonSave(entityType);

        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode());

        sendSmsToStudent(entityType.getStudentPhoneNumber(),entityType);
        sendSmsToMarket(marketingEmployee.getPhoneNumber(),entityType,marketingEmployee);
        sendSmsToStudent(entityType.getParentPhoneNumber(),entityType);

        return studentScholarInfo;
    }

    @Override
    public List<StudentScholarInfo> search(final StudentScholarSearchCriteria studentSearchCriteria) {
        return StudentScholarSearchService
                .search(studentSearchCriteria)
                .with(studentScholarRepository);
    }

    @Override
    public Optional<StudentScholarInfo> update(String applicationNumber, StudentScholarInfo studentScholarInfo)
    {
        return Optional.ofNullable(studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber))
                .map(scholar -> this.commonSave(studentScholarInfo))
                .orElseThrow(() ->  new SmsException("Student Scholar Update Error", "What you trying to Update its not available"));
    }

    public void sendSmsToStudent(final String phoneNumber,StudentScholarInfo studentScholarInfo)
    {
        final Message message = messageRepository.findById(1);

        SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set(studentScholarInfo.getName())
                .on(SMSDetails::getPhoneNumber).set(phoneNumber)
                .on(SMSDetails::getMessage).set("Hi "+studentScholarInfo.getName()+",Application No:"+studentScholarInfo.getApplicationNumber()+","+message.getMessage()).build()).apply(smsConfig);
    }
    public void sendSmsToMarket(final String phoneNumber, StudentScholarInfo studentScholarInfo, final MarketingEmployee marketingEmployee)
    {
        final Message message = messageRepository.findById(2);

        final String welcomeMessage = "Hi "+marketingEmployee.getName()+",Name:"+studentScholarInfo.getName()+",Application Number:"+studentScholarInfo.getApplicationNumber()+message.getMessage();

        SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set(studentScholarInfo.getName())
                .on(SMSDetails::getPhoneNumber).set(phoneNumber)
                .on(SMSDetails::getMessage).set(welcomeMessage).build()).apply(smsConfig);
    }
}
