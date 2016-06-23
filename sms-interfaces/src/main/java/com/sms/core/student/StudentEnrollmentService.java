package com.sms.core.student;

import com.sms.core.common.Reader;
import com.sms.core.repositery.StudentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by rmurugaian on 6/23/2016. <p></p>
 */
public interface StudentEnrollmentService {
    /**
     * @param studentInfo
     * @return
     */
    Reader<StudentEnrollmentConfig, StudentInfo> save(final StudentInfo studentInfo);

    /**
     * @return
     */
    Reader<StudentEnrollmentConfig, List<StudentInfo>> delete();

    /**
     * @param id
     * @return
     */
    Reader<StudentRepository, Void> delete(final Long id);

    /**
     *
     * @param id
     * @return
     */
    Reader<StudentRepository, Optional<StudentInfo>> findById(Long id);
}
