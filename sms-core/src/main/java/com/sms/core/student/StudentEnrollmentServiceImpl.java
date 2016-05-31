package com.sms.core.student;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.CourseRepository;
import com.sms.core.repositery.SchemeRepository;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseServiceConvertorImpl<StudentInfo, Student> {

    private BranchRepository branchRepository;
    private CourseRepository courseRepository;
    private SchemeRepository schemeRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository,
                                        final BranchRepository branchRepository,
                                        final CourseRepository courseRepository,
                                        final SchemeRepository schemeRepository) {

        super(studentRepository,
                (studentInfo) ->
                        Student.toBuilder(studentInfo)
                                .withCode(new StringBuilder(studentInfo.getBranchCode())
                                        .append(LocalDateTime.now().getYear())
                                        .append(String.format("%06d", studentRepository.count() + 1))
                                        .toString())
                                .withBranch(branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                                .withCourse(courseRepository.findByCodeIgnoreCase(studentInfo.getCourseCode()))
                                .withScheme(schemeRepository.findByCodeIgnoreCase(studentInfo.getSchemeCode()))
                                .build(),
                (student) -> StudentInfo.toBuilder(student).build());
        this.branchRepository = branchRepository;
        this.courseRepository = courseRepository;
        this.schemeRepository = schemeRepository;
    }

    @Override
    protected Student buildToPersistObject(Long id, StudentInfo studentInfo) {

        return Student.toBuilder(studentInfo)
                .withId(id)
                .withCode(studentInfo.getCode())
                .withBranch(branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                .withCourse(courseRepository.findByCodeIgnoreCase(studentInfo.getCourseCode()))
                .withScheme(schemeRepository.findByCodeIgnoreCase(studentInfo.getSchemeCode()))
                .build();
    }
}