package com.sms.core.student;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ram on 6/26/2016.
 * <p></p>
 */
public interface StudentFacade {

    /**
     * @param studentInfo
     * @return
     */
    StudentInfo save(final StudentInfo studentInfo);

    /**
     * @return
     */
    List<StudentInfo> findAll();

    /**
     * @param id
     * @return
     */
    Void delete(final Long id);

    /**
     * @param id
     * @return
     */
    Optional<StudentInfo> findById(Long id);

    /**
     *
     * @param code
     * @return
     */
    Optional<StudentInfo> findByCode(String code);

    /**
     *
     * @param applicationNumber
     * @return
     */
    Optional<StudentInfo> findByScholarship(final String applicationNumber);
}
