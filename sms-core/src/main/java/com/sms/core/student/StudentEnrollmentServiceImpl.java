package com.sms.core.student;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.Caste;
import com.sms.core.Gender;
import com.sms.core.Religion;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentEnrollmentService")
@Transactional
public class StudentEnrollmentServiceImpl extends BaseServiceConvertorImpl<StudentInfo, Student> {

    private BranchRepository branchRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(final StudentRepository studentRepository, final BranchRepository branchRepository) {

        super(studentRepository,
                (studentInfo) ->
                        Student.toBuilder(studentInfo)
                                .withBranch(branchRepository.findByCodeIgnoreCase(studentInfo.getBranchCode()))
                                .build(),
                (student) -> StudentInfo.toBuilder(student).build());
        this.branchRepository = branchRepository;
    }

    @Override
    protected Student buildToPersistObject(Long id, StudentInfo student) {

        return Student.builder()
                .withId(id)
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withMailId(student.getMailId())
                .withAddress(student.getAddress())
                .withGender(Gender.valueOf(student.getGender()))
                .withReligion(Religion.valueOf(student.getReligion()))
                .withCaste(Caste.valueOf(student.getCaste()))
                .withGuardians(student.getGuardians())
                .withBranch(branchRepository.findByCodeIgnoreCase(student.getBranchCode()))
                .build();
    }
}