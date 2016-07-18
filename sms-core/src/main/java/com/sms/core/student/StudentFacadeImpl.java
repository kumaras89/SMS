package com.sms.core.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${JOINED_WELCOME_MESSAGE_FOR_STUDENT}")
    private static String WELCOME_MESSAGE;

    @Autowired
    private StudentEnrollmentConfig seConfig;

    @Override
    public StudentInfo save(final StudentInfo studentInfo) {

        //Sending Message to Student scholars
        /*System.out.println("Hard coded value"+WELCOME_MESSAGE);
        SendMessage.sendingMessageToParticular(studentInfo.getPhoneNumber(),WELCOME_MESSAGE);*/

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

    @Override
    public List<StudentInfo> search(StudentSearchCriteria studentSearchCriteria) {
        return StudentSearchService
                .search(studentSearchCriteria)
                .local(StudentEnrollmentConfig::getStuRepo)
                .with(seConfig);
    }
}
