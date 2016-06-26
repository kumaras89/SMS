package com.sms.core.student;

import com.sms.core.common.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by rmurugaian on 6/23/2016. <p></>
 */
public class StudentEnrollmentConverter {

    public static Student convertTo(final StudentInfo studentInfo) {

        return Student.builder()
            .with(Student::getName, studentInfo.getName())
            .with(Student::getAge, studentInfo.getAge())
            .with(Student::getPhoneNumber, studentInfo.getPhoneNumber())
            .with(Student::getFatherOrMotherName, studentInfo.getFatherOrMotherName())
            .with(Student::getAlternatePhoneNumber, studentInfo.getAlternatePhoneNumber())
            .with(Student::getDateOfBirth, studentInfo.getDateOfBirth())
            .with(Student::getMailId, studentInfo.getMailId())
            .with(Student::getGender, Gender.valueOf(studentInfo.getGender()))
            .with(Student::getCaste, Caste.valueOf(studentInfo.getCaste()))
            .with(Student::getOtherLanguages, studentInfo.getOtherLanguages())
            .with(Student::getReligion, Religion.valueOf(studentInfo.getReligion()))
            .with(Student::getGuardians, studentInfo.getGuardians())
            .with(Student::getEducationDetails, studentInfo.getEducationDetails())
            .with(Student::getAddress, studentInfo.getAddress())
            .with(Student::getEnglishFluency, Rating.valueOf(studentInfo.getEnglishFluency()))
            .with(Student::getMaritalStatus, MaritalStatus.valueOf(studentInfo.getMaritalStatus()))
            .with(Student::getNationality, studentInfo.getNationality())
            .with(Student::getStatus, StudentStatus.valueOf(studentInfo.getStatus()))
            .with(Student::getCreatedDate, studentInfo.getCreatedDate())
            .with(Student::getLastModifiedDate, studentInfo.getLastModifiedDate())
            .with(Student::getCode, new StringBuilder(studentInfo.getBranchCode())
                                                  .append(studentInfo.getCourseCode())
                                                  .append(LocalDateTime.now().getYear())
                                                  .toString())
            .with(Student::getCreatedDate, new Date())
            .with(Student::getLastModifiedDate, new Date())
            .on(Student::getSslcMarkDetails).set(studentInfo.getSslcMarkDetails())
            .on(Student::getHscMarkDetails).set(studentInfo.getHscMarkDetails())
            .build();
    }

    public static StudentInfo convertTo(final Student student) {
        return StudentInfo.builder()
            .with(StudentInfo::getId, student.getId())
            .with(StudentInfo::getCode, student.getCode())
            .on(StudentInfo::getFatherOrMotherName).set(student.getFatherOrMotherName())
            .with(StudentInfo::getName, student.getName())
            .with(StudentInfo::getAge, student.getAge())
            .with(StudentInfo::getPhoneNumber, student.getPhoneNumber())
            .with(StudentInfo::getAlternatePhoneNumber, student.getAlternatePhoneNumber())
            .with(StudentInfo::getDateOfBirth, student.getDateOfBirth())
            .with(StudentInfo::getMailId, student.getMailId())
            .with(StudentInfo::getGender, student.getGender().name())
            .with(StudentInfo::getCaste, student.getCaste().name())
            .with(StudentInfo::getReligion, student.getReligion().name())
            .with(StudentInfo::getGuardians, student.getGuardians())
            .with(StudentInfo::getEducationDetails, student.getEducationDetails())
            .with(StudentInfo::getAddress, student.getAddress())
            .with(StudentInfo::getEnglishFluency, student.getEnglishFluency().name())
            .with(StudentInfo::getMaritalStatus, student.getMaritalStatus().name())
            .on(StudentInfo::getFeesInfos).set(StudentFeesConverter.convertTo(student.getStudentFees()))
            .with(StudentInfo::getNationality, student.getNationality())
            .with(StudentInfo::getBranchCode, student.getBranch().getCode())
            .with(StudentInfo::getCourseCode, student.getCourse().getCode())
            .with(StudentInfo::getSchemeCode, student.getScheme().getCode())
            .with(StudentInfo::getStatus, student.getStatus().name())
            .with(StudentInfo::getCreatedDate, student.getCreatedDate())
            .with(StudentInfo::getLastModifiedDate, student.getLastModifiedDate())
            .with(StudentInfo::getMarketingEmployeeCode, student.getMarketingEmployee().getCode())
            .on(StudentInfo::getHscMarkDetails).set(student.getHscMarkDetails())
            .on(StudentInfo::getSslcMarkDetails).set(student.getSslcMarkDetails())
            .on(StudentInfo::getOtherLanguages).set(student.getOtherLanguages())
                .build();
    }

}
