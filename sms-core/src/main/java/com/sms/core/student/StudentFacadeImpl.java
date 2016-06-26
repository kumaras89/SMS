package com.sms.core.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ram on 6/26/2016.
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

    @Autowired
    private StudentEnrollmentConfig seConfig;

    @Autowired
    private StudentEnrollmentService service;


    @Override
    public StudentInfo save(final StudentInfo studentInfo) {
        return service.save(studentInfo).with(seConfig);
    }

    @Override
    public List<StudentInfo> findAll() {
        return service.findAll().with(seConfig);
    }

    @Override
    public Void delete(final Long id) {
        return service.findAll(id).with(seConfig.getStuRepo());
    }

    @Override
    public Optional<StudentInfo> findById(final Long id) {
        return service.findById(id).with(seConfig.getStuRepo());
    }
}
