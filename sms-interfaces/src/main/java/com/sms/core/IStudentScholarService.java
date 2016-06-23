package com.sms.core;

import com.sms.core.student.StudentInfo;
import com.sms.core.student.StudentScholarInfo;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 6/20/2016.
 */
public interface IStudentScholarService
{

    Optional<StudentScholarInfo> findById(final Long id);

    List<StudentScholarInfo> findAll();

    Optional<StudentScholarInfo> save(final StudentScholarInfo entityType);

}
