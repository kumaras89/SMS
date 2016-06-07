package com.sms.core.student;

import com.sms.core.common.Builder;

import java.util.Date;
import java.util.Set;

public class StudentInfo {

    private String code;
    private String name;
    private int age;
    private String phoneNumber;
    private String alternatePhoneNumber;
    private Date dateOfBirth;
    private String mailId;
    private Address address;
    private String branchCode;
    private String gender;
    private String caste;
    private String religion;
    private Set<Guardian> guardians;
    private Set<EducationDetail> educationDetails;
    private String nationality;
    private String maritalStatus;
    private String englishFluency;
    private String courseCode;
    private String schemeCode;
    private String status;
    private Date createdDate;
    private Date lastModifiedDate;

    public StudentInfo() {
    }

    public static Builder<StudentInfo> builder() {
        return Builder.of(StudentInfo.class);
    }

    public static Builder<StudentInfo> toBuilder(final Student student) {
        return builder()
                .with(StudentInfo::getCode, student.getCode())
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
                .with(StudentInfo::getNationality, student.getNationality())
                .with(StudentInfo::getBranchCode, student.getBranch().getCode())
                .with(StudentInfo::getCourseCode, student.getCourse().getCode())
                .with(StudentInfo::getSchemeCode, student.getScheme().getCode())
                .with(StudentInfo::getStatus, student.getStatus().name())
                .with(StudentInfo::getCreatedDate, student.getCreatedDate())
                .with(StudentInfo::getLastModifiedDate, student.getLastModifiedDate());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMailId() {
        return mailId;
    }

    public Address getAddress() {
        return address;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getGender() {
        return gender;
    }

    public String getCaste() {
        return caste;
    }

    public String getReligion() {
        return religion;
    }

    public Set<Guardian> getGuardians() {
        return guardians;
    }

    public Set<EducationDetail> getEducationDetails() {
        return educationDetails;
    }

    public String getNationality() {
        return nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getEnglishFluency() {
        return englishFluency;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}