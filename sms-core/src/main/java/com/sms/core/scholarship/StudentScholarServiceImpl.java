package com.sms.core.scholarship;

import com.sms.core.SmsException;
import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.message.MessageConfig;
import com.sms.core.message.SMSDetails;
import com.sms.core.message.SMSSender;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.MarketingEmployeeRepository;
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
    private final MessageConfig messageConfig;


    @Value("${JOINED_WELCOME_MESSAGE_FOR_SCHOLARSHIP}")
    private String welcomeMessage;

    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository,
                                     final MarketingEmployeeRepository marketingEmployeeRepository ,
                                     final BranchRepository branchRepository,
                                     final MessageConfig messageConfig) {
        this.studentScholarRepository = studentScholarRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.branchRepository = branchRepository;
        this.messageConfig = messageConfig;
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



    @Override
    public Optional<StudentScholarInfo> save(final StudentScholarInfo entityType) {
        Optional.ofNullable(studentScholarRepository
                                        .findByApplicationNumberIgnoreCase(entityType.getApplicationNumber()))
                                        .ifPresent( studentScholar -> new SmsException("applicationNumber", String
                .format("Scholarship already exist for %s application number", entityType.getApplicationNumber())));


        final StudentScholar scholar = StudentScholar.toBuilder(entityType)
                .on(StudentScholar::getMarketingEmployee).set(marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode()))
                .on(StudentScholar::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .build();


        final Optional<StudentScholarInfo> newStudent = Optional.of(studentScholarRepository
                                                                          .saveAndFlush(scholar))
                                                                 .map(StudentScholarServiceImpl::scholarToInfo);

        SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set(scholar.getName())
            .on(SMSDetails::getPhoneNumber).set(scholar.getStudentPhoneNumber())
            .on(SMSDetails::getMessage).set(welcomeMessage).build()).apply(messageConfig);
        return newStudent;
    }

}
