package com.sms.core.student;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public StudentInfo save(final StudentInfo studentInfo) {
        return StudentEnrollmentService.save(studentInfo).with(seConfig);
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
}
