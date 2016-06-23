package com.sms.core.student;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.common.Builder;
import com.sms.core.repositery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseServiceConvertorImpl<StudentInfo, Student> {

    private final BranchRepository branchRepository;
    private final CourseRepository courseRepository;
    private final SchemeRepository schemeRepository;
    private final MarketingEmployeeRepository marketingEmployeeRepository;
    private final FeesParticularRepository feesParticularRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository,
                                        final BranchRepository branchRepository,
                                        final CourseRepository courseRepository,
                                        final SchemeRepository schemeRepository,
                                        final MarketingEmployeeRepository marketingEmployeeRepository,
                                        final FeesParticularRepository feesParticularRepository) {

        super(studentRepository,
                (studentInfo) ->
                        Student.toBuilder(studentInfo)
                                .with(Student::getCode, new StringBuilder(studentInfo.getBranchCode())
                                        .append(studentInfo.getCourseCode())
                                        .append(LocalDateTime.now().getYear())
                                        .append(String.format("%06d", studentRepository.count() + 1))
                                        .toString())
                                .on(Student::getStudentFees ).set(
                                                             studentInfo.getFeesInfos()
                                                             .stream().map(feesInfo ->  StudentFees.toBuilder
                                                                 (feesInfo).apply(feesParticularRepository
                                                                 .findByCodeIgnoreCase(feesInfo.getFeesParticularCode())))
                                                                 .map(Builder::build)
                                                                 .collect(Collectors.toList()))
                                .with(Student::getBranch, branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                                .with(Student::getCourse, courseRepository.findByCodeIgnoreCase(studentInfo.getCourseCode()))
                                .with(Student::getScheme, schemeRepository.findByCodeIgnoreCase(studentInfo.getSchemeCode()))
                                .with(Student::getMarketingEmployee, marketingEmployeeRepository.findByCodeIgnoreCase(studentInfo.getMarketingEmployeeCode()))
                                .with(Student::getCreatedDate, new Date())
                                .with(Student::getLastModifiedDate, new Date())
                                .build(),
                (student) -> StudentInfo.toBuilder(student).build());
        this.branchRepository = branchRepository;
        this.courseRepository = courseRepository;
        this.schemeRepository = schemeRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.feesParticularRepository = feesParticularRepository;
    }

    @Override
    protected Student buildToPersistObject(Long id, StudentInfo studentInfo) {

        return Student.toBuilder(studentInfo)
                .with(Student::getId, id)
                .with(Student::getCode, studentInfo.getCode())
                .with(Student::getBranch, branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                .with(Student::getCourse, courseRepository.findByCodeIgnoreCase(studentInfo.getCourseCode()))
                .with(Student::getScheme, schemeRepository.findByCodeIgnoreCase(studentInfo.getSchemeCode()))
                .with(Student::getMarketingEmployee, marketingEmployeeRepository.findByCodeIgnoreCase(studentInfo.getMarketingEmployeeCode()))
                .with(Student::getLastModifiedDate, new Date())
                .build();
    }
}