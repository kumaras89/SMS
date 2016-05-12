package com.sms.core.student;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseServiceConvertorImpl<StudentInfo, Student> {

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository, final BranchRepository branchRepository) {

        super(studentRepository,
                (studentInfo) ->
                        Student.toBuilder(studentInfo)
                                .withBranch(branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                                .build(),
                (student) -> StudentInfo.toBuilder(student).build());
    }

    @Override
    protected StudentInfo buildToPersistObject(Long id, StudentInfo student) {

        return StudentInfo.builder()
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withMailId(student.getMailId())
                .withAddress(student.getAddress())
                .withBranchCode(student.getBranchCode())
                .build();
    }
}