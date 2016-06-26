package com.sms.core.student;

import com.sms.core.common.Do;
import com.sms.core.common.FunctionUtils;
import com.sms.core.common.Reader;
import com.sms.core.repositery.StudentRepository;
import javaslang.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    @Override
    public Reader<StudentEnrollmentConfig, StudentInfo> save(final StudentInfo studentInfo) {
        return Reader.of
            (sec -> {
                return Do.of(studentInfo)
                        .then(StudentEnrollmentConverter::convertTo)
                        .then(s -> StudentUpdater.updateStudent(studentInfo).apply(Tuple.of(sec,s)))
                        .then(student -> sec.getStuRepo().save(student))
                        .then(StudentEnrollmentConverter::convertTo).get();
                }
            );
    }


    @Override
    public Reader<StudentEnrollmentConfig, List<StudentInfo>> findAll() {
        return Reader.of
            (sec -> Do.of(sec.getStuRepo().findAll())
                      .then(FunctionUtils
                          .asList(
                              StudentEnrollmentConverter::convertTo))
                      .get()
            );
    }

    @Override
    public Reader<StudentRepository, Void> findAll(final Long id) {
        return Reader.of(sec -> Do.of(id).thenVoid(v -> sec.delete(v)).get());
    }

    @Override
    public Reader<StudentRepository, Optional<StudentInfo>> findById(final Long id) {
        return Reader.of(sr -> Do.of(sr.findOne(id))
                                    .then(StudentEnrollmentConverter::convertTo)
                                    .then(Optional::ofNullable)
            .get());
    }
}