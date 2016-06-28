package com.sms.core;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.student.StudentInfo;
import com.sms.core.student.StudentScholar;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by sathish on 6/20/2016.
 */
@Service
@Transactional
public class StudentScholarServiceImpl implements IStudentScholarService
{
    private final Converter<StudentScholarInfo, StudentScholar> sourceConverter;
    private final Converter<StudentScholar, StudentScholarInfo> destinationConverter;
    private final StudentScholarRepository studentScholarRepository;

    @Autowired
    public StudentScholarServiceImpl(final StudentScholarRepository studentScholarRepository)
    {
        this.studentScholarRepository = studentScholarRepository;
        this.sourceConverter = (studentScholarInfo) ->
                StudentScholar.toBuilder(studentScholarInfo).build();
        this.destinationConverter = (studentScholar) -> StudentScholarInfo.toBuilder(studentScholar).build();
    }

    @Override
    public List<StudentScholarInfo> findAll() {
        return this.studentScholarRepository.findAll().stream().map(destinationConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentScholarInfo> findById(String applicationNumber) {
        return Optional.of(this.studentScholarRepository.findByApplicationNumberIgnoreCase(applicationNumber)).map(destinationConverter::convert);
    }

    @Override
    public Optional<StudentScholarInfo> save(StudentScholarInfo entityType) {
        StudentScholar alreadyexist = studentScholarRepository.findByApplicationNumberIgnoreCase(entityType.getApplicationNumber());
        if(alreadyexist != null) {
            throw new SmsException("applicationNumber", String.format("Scholarship already exist for %s application number", entityType.getApplicationNumber()));
        }
        return  Optional.of(studentScholarRepository.saveAndFlush(sourceConverter.convert(entityType))).map(destinationConverter::convert);
    }

}
