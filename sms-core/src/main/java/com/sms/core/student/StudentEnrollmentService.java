package com.sms.core.student;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.Reader;
import com.sms.core.repositery.StudentRepository;
import com.sms.core.scholarship.StudentScholarService;
import javaslang.Tuple;

import java.util.List;
import java.util.Optional;

public class StudentEnrollmentService {

    /**
     *
     * @param studentInfo
     * @return
     */
    public static Reader<StudentEnrollmentConfig, StudentInfo> save(final StudentInfo studentInfo) {
        return Reader.of
            (sec -> Do.of(studentInfo)
                                .then(StudentEnrollmentConverter::convert)
                                .then(s -> StudentUpdater.updateStudent(studentInfo).apply(Tuple.of(sec, s)))
                                .then(student -> sec.getStuRepo().save(student))
                                .then(StudentEnrollmentConverter::convertTo)
                                .then(si -> {
                                    if(si.getApplicationNumber() != null && !si.getApplicationNumber().isEmpty()) {
                                        sec.getStudScholarServ().enrollStudent(si.getApplicationNumber());
                                    }
                                    return si;
                                })
                                .get()
                    );
    }


    /**
     *
     * @return
     */
    public static Reader<StudentEnrollmentConfig, List<StudentInfo>> findAll() {
        return Reader.of(sec ->
                FList.of(sec.getStuRepo().findAll())
                        .map(StudentEnrollmentConverter::convertTo)
                        .get());
    }


    /**
     *
     * @param id
     * @return
     */
    public static Reader<StudentRepository, Void> findAll(final Long id) {
        return Reader.of(sec -> Do.of(id).thenVoid(v -> sec.delete(v)).get());
    }

    /**
     *
     * @param id
     * @return
     */
    public static Reader<StudentRepository, Optional<StudentInfo>> findById(final Long id) {
        return Reader.of(sr -> Do.of(sr.findOne(id))
                                    .then(StudentEnrollmentConverter::convertTo)
                                    .then(Optional::ofNullable)
            .get());
    }

    /**
     *
     * @param code
     * @return
     */
    public static Reader<StudentRepository, Optional<StudentInfo>> findByCode(final String code) {
        return Reader.of(sr -> Do.of(sr.findByCode(code))
                .then(StudentEnrollmentConverter::convertTo)
                .then(Optional::ofNullable)
                .get());
    }

    /**
     *
     * @param applicationNumber
     * @return
     */
    public static Reader<StudentScholarService, Optional<StudentInfo>> findByStudentScholarship(final String
                                                                                                     applicationNumber){
       return Reader.of(studentScholarService -> Do.of(studentScholarService.findByApplicationNumber(applicationNumber))
                                                            .then(StudentInfoConverter::convert).get());

    }
}