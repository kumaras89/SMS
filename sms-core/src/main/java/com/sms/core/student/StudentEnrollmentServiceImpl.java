package com.sms.core.student;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.CourseRepository;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseServiceConvertorImpl<StudentInfo, Student> {

    private BranchRepository branchRepository;
    private CourseRepository courseRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository,
                                        final BranchRepository branchRepository,
                                        final CourseRepository courseRepository) {

        super(studentRepository,
                (studentInfo) ->
                        Student.toBuilder(studentInfo)
                                .withBranch(branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                                .withCourse(courseRepository.findByCodeIgnoreCase(studentInfo.getCourseCode()))
                                .build(),
                (student) -> StudentInfo.toBuilder(student).build());
        this.branchRepository = branchRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    protected Student buildToPersistObject(Long id, StudentInfo student) {

        return Student.toBuilder(student)
                .withId(id)
                .withBranch(branchRepository.findByCodeIgnoreCase(student.getBranchCode()))
                .withCourse(courseRepository.findByCodeIgnoreCase(student.getCourseCode()))
                .build();
    }
}