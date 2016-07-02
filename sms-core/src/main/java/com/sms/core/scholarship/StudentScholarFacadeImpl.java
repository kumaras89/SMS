package com.sms.core.scholarship;

import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.student.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ram on 6/26/2016.
 * <p></p>
 */
@Service
@Transactional
public class StudentScholarFacadeImpl implements StudentScholarFacade {

    private StudentScholarRepository studentScholarRepository;

    @Autowired
    public StudentScholarFacadeImpl(StudentScholarRepository studentScholarRepository) {
        this.studentScholarRepository = studentScholarRepository;
    }

    @Override
    public List<StudentScholarInfo> search(StudentScholarSearchCriteria studentSearchCriteria) {
        return StudentScholarSearchService
                .search(studentSearchCriteria)
                .with(studentScholarRepository);
    }
}
