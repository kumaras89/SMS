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
    private final SendToAllImp sendToAllImp;

    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository,
                                     final MarketingEmployeeRepository marketingEmployeeRepository ,
                                     final BranchRepository branchRepository,
                                     final SendToAllImp sendToAllImp)
    {
        this.studentScholarRepository = studentScholarRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.branchRepository = branchRepository;
        this.sendToAllImp = sendToAllImp;
    }

    private SendingDetails SetAllDataForSendingDetails(StudentScholarInfo studentScholarInfo)
    {
        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentScholarInfo.getMarketingEmployeeCode());

        return SendingDetails.builder().on(SendingDetails::getGuardianPhoneNumber).set(studentScholarInfo.getParentPhoneNumber())
                .on(SendingDetails::getStudentPhoneNumber).set(studentScholarInfo.getStudentPhoneNumber())
                .on(SendingDetails::getStudentApplicationNumber).set(studentScholarInfo.getApplicationNumber())
                .on(SendingDetails::getStudentName).set(studentScholarInfo.getName())
                .on(SendingDetails::getMarketingEmployeeName).set(marketingEmployee.getName())
                .on(SendingDetails::getMarketingEmployeePhonenumber).set(marketingEmployee.getPhoneNumber())
                .build();
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

        Optional<StudentScholarInfo> studentScholarInfo = Optional.of(studentScholarRepository.saveAndFlush(StudentScholar.toBuilder(entityType)
                .on(StudentScholar::getMarketingEmployee).set(marketingEmployeeRepository.findByCodeIgnoreCase(entityType.getMarketingEmployeeCode()))
                .on(StudentScholar::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .build())).map(StudentScholarServiceImpl::scholarToInfo);

        sendToAllImp.sendAll(SetAllDataForSendingDetails(entityType));

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
        final StudentScholar studentExists = this.studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber);
        final StudentScholar studentScholarModified = studentScholarRepository.saveAndFlush(Builder.of(StudentScholar.class, studentExists)
                .on(StudentScholar::getLastModifiedDate).set(new Date()).build());
        return Optional.of(studentScholarModified)
                .map(StudentScholarServiceImpl::scholarToInfo);
    }
}
