package com.sms.core.student;

import com.sms.core.common.Builder;

import java.util.Optional;

public class StudentInfoConverter {

    /**
     *
     * @param studentScholarInfo
     * @return
     */
    public static Optional<StudentInfo> convert(final Optional<StudentScholarInfo> studentScholarInfo) {
        return studentScholarInfo.map(StudentInfoConverter::getStudentInfo);
    }

    private static StudentInfo getStudentInfo(final StudentScholarInfo studentScholarInfo) {
        return Builder.of(StudentInfo.class)
            .on(StudentInfo::getApplicationNumber).set(studentScholarInfo.getApplicationNumber())//change
            .on(StudentInfo::getName).set(studentScholarInfo.getName())
            .on(StudentInfo::getDateOfBirth).set(studentScholarInfo.getDateOfBirth())
            .on(StudentInfo::getAge).set(studentScholarInfo.getAge())
            .on(StudentInfo::getGender).set(studentScholarInfo.getGender())
            .on(StudentInfo::getMaritalStatus).set(studentScholarInfo.getMaritalStatus())
            .on(StudentInfo::getPhoneNumber).set(studentScholarInfo.getStudentPhoneNumber())
            .on(StudentInfo::getAlternatePhoneNumber).set(studentScholarInfo.getParentPhoneNumber())
            .on(StudentInfo::getEducationDetails).set(studentScholarInfo.getEducationDetails())
            .on(StudentInfo::getAddress).set(studentScholarInfo.getAddress())
            .on(StudentInfo::getNationality).set(studentScholarInfo.getNationality())
            .on(StudentInfo::getReligion).set(studentScholarInfo.getReligion())
            .on(StudentInfo::getCaste).set(studentScholarInfo.getCaste())
            .on(StudentInfo::getFatherOrMotherName).set(studentScholarInfo.getFatherOrMotherName())
            .on(StudentInfo::getMailId).set(studentScholarInfo.getEmailId())
            .on(StudentInfo::getLastModifiedDate).set(studentScholarInfo.getLastModifiedDate())
            .on(StudentInfo::getCreatedDate).set(studentScholarInfo.getCreatedDate())
                .on(StudentInfo::getBranchCode).set(studentScholarInfo.getBranchCode())
                .on(StudentInfo::getMarketingEmployeeCode).set(studentScholarInfo.getMarketingEmployeeCode()).build();
    }
}
