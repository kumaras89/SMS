package com.sms.core.scholarship;

import com.sms.core.SmsException;
import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SendMessageToAll;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.student.StudentInfo;
import com.sms.core.student.StudentScholar;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentScholarServiceImpl implements StudentScholarService {


    private final StudentScholarRepository studentScholarRepository;
    private final MarketingEmployeeRepository marketingEmployeeRepository;
    private final BranchRepository branchRepository;
    private final SendMessageToAll sendMessageToAll;

    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository,
                                     final MarketingEmployeeRepository marketingEmployeeRepository,
                                     final BranchRepository branchRepository,
                                     final SendMessageToAll sendMessageToAll) {
        this.studentScholarRepository = studentScholarRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.branchRepository = branchRepository;
        this.sendMessageToAll = sendMessageToAll;
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
                .ifPresent(studentScholar -> new SmsException("applicationNumber", String
                        .format("Scholarship already exist for %s application number", entityType.getApplicationNumber())));

        final MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode());

        Optional<StudentScholarInfo> studentScholarInfo = Optional.of(studentScholarRepository.saveAndFlush(StudentScholar.toBuilder(entityType)
                .on(StudentScholar::getMarketingEmployee).set(marketingEmployee)
                .on(StudentScholar::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .build())).map(StudentScholarServiceImpl::scholarToInfo);

        List<SendingDetails> sendingDetailsList = new ArrayList();


        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(entityType.getStudentPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(entityType.getName())
                                .append(",Application No:")
                                .append(entityType.getApplicationNumber())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_STUDENT")
                .build());

        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(entityType.getParentPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(entityType.getName())
                                .append(",Application No:")
                                .append(entityType.getApplicationNumber())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_STUDENT")
                .build());

        sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(entityType.getStudentPhoneNumber())
                .on(SendingDetails::getSenderMessage).set(
                        new StringBuilder("Hi")
                                .append(marketingEmployee.getName())
                                .append(",Name:")
                                .append(entityType.getName())
                                .append(",Application No:")
                                .append(entityType.getApplicationNumber())
                                .toString())
                .on(SendingDetails::getMessageCode).set("SMS_MARKET_EMP")
                .build());

        sendMessageToAll.sendAll(sendingDetailsList);

        return studentScholarInfo;
    }

    @Override
    public List<StudentScholarInfo> search(final StudentScholarSearchCriteria studentSearchCriteria) {
        return StudentScholarSearchService
                .search(studentSearchCriteria)
                .with(studentScholarRepository);
    }

    @Override
    public Optional<StudentScholarInfo> update(String applicationNumber, StudentScholarInfo entityType) {
        StudentScholar studentScholar = studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber);
        StudentScholar modifiedStudentScholar = studentScholarRepository.saveAndFlush(StudentScholar.toBuilder(entityType)
                .on(StudentScholar::getMarketingEmployee).set(marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode()))
                .on(StudentScholar::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .on(StudentScholar::getCreatedDate).set(studentScholar.getCreatedDate())
                .build());
        return Optional.of(modifiedStudentScholar).map(StudentScholarServiceImpl::scholarToInfo);
    }
}
