package com.sms.core.student;

import com.sms.core.StudentScholarBaseServiceConvertorImpl;
import com.sms.core.repositery.StudentScholarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sathish on 6/20/2016.
 */
@Service(value = "studentScholarService")
@Transactional
public class StudentScholarServiceImpl extends StudentScholarBaseServiceConvertorImpl<StudentScholarInfo,StudentScholar>
{
    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository) {

        super(studentScholarRepository,
                (studentScholarInfo) ->
                        StudentScholar.toBuilder(studentScholarInfo).build(),
                (studentScholar) -> StudentScholarInfo.toBuilder(studentScholar).build());
    }

    @Override
    protected StudentScholar buildToPersistObject(Long id, StudentScholarInfo studentScholarInfo) {

        return StudentScholar.toBuilder(studentScholarInfo).on(c -> c.getId()).set(id)
                .build();
    }
}
