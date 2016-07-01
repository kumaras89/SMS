package com.sms.core.student;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.Reader;
import com.sms.core.repositery.StudentRepository;
import javaslang.Tuple;

import java.util.List;
import java.util.Optional;

public class StudentEnrollmentService {

  
    public static Reader<StudentEnrollmentConfig, StudentInfo> save(final StudentInfo studentInfo) {
        return Reader.of
            (sec -> {
                        return Do.of(studentInfo)
                                .then(StudentEnrollmentConverter::convert)
                                .then(s -> StudentUpdater.updateStudent(studentInfo).apply(Tuple.of(sec, s)))
                                .then(student -> sec.getStuRepo().save(student))
                                .then(StudentEnrollmentConverter::convertTo).get();
                    }
            );
    }


  
    public static Reader<StudentEnrollmentConfig, List<StudentInfo>> findAll() {
        return Reader.of(sec ->
                FList.of(sec.getStuRepo().findAll())
                        .map(StudentEnrollmentConverter::convertTo)
                        .get());
    }

  
    public static Reader<StudentRepository, Void> findAll(final Long id) {
        return Reader.of(sec -> Do.of(id).thenVoid(v -> sec.delete(v)).get());
    }

  
    public static Reader<StudentRepository, Optional<StudentInfo>> findById(final Long id) {
        return Reader.of(sr -> Do.of(sr.findOne(id))
                                    .then(StudentEnrollmentConverter::convertTo)
                                    .then(Optional::ofNullable)
            .get());
    }

    public static Reader<StudentRepository, Optional<StudentInfo>> findByCode(final String code) {
        return Reader.of(sr -> Do.of(sr.findByCode(code))
                .then(StudentEnrollmentConverter::convertTo)
                .then(Optional::ofNullable)
                .get());
    }
}