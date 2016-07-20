package com.sms.core.scholarship;

import com.sms.core.student.StudentScholarInfo;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 6/20/2016. <p></p>
 */
public interface StudentScholarService {

    /**
     * @param applicationNumber
     * @return
     */
    Optional<StudentScholarInfo> findByApplicationNumber(final String applicationNumber);

    /**
     * @return
     */
    List<StudentScholarInfo> findAll();

    /**
     * @param entityType
     * @return
     */
    Optional<StudentScholarInfo> save(final StudentScholarInfo entityType);

    /**
     *
     * @param applicationNumber
     * @return
     */
    Optional<StudentScholarInfo> enrollStudent(final String applicationNumber);

}
