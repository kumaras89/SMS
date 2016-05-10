package com.sms.core.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.core.BaseStudentPortalFindServiceImpl;
import com.sms.core.repositery.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseStudentPortalFindServiceImpl<Student> {

    public static final String QUALIFIER = "studentEnrollmentService";

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository) {
        super(studentRepository);
    }

    @Override
    protected Student buildToPersistObject(final Long id, final Student student) {

        return Student.builder()
                .withId(id)
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withMailId(student.getMailId())
                .withAddress(student.getAddress())
                .withBranch(student.getBranch())
                .build();
    }
}

