package com.sms.core.student;

import com.sms.core.common.Builder;
import com.sms.core.common.Gender;

import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 6/20/2016.
 */
public class StudentScholarInfo
{
    private Long id;
    private String code;

    private String name;
    private Date dateOfBirth;
    private int age;
    private String gender;
    private String maritalStatus;
    private String studentPhoneNumber;
    private String parentPhoneNumber;
    private String fatherOrMotherName;
    private String nationality;
    private String caste;
    private String religion;

    private Set<EducationDetail> educationDetails;
    private Address address;

    public StudentScholarInfo()
    {

    }

    public static Builder<StudentScholarInfo> builder() {
        return Builder.of(StudentScholarInfo.class);
    }

    public static Builder<StudentScholarInfo> toBuilder(final StudentScholar studentScholar) {
        return builder()
                .with(StudentScholarInfo::getId, studentScholar.getId())
                .with(StudentScholarInfo::getCode, studentScholar.getCode())
                .with(StudentScholarInfo::getName, studentScholar.getName())
                .with(StudentScholarInfo::getDateOfBirth, studentScholar.getDateOfBirth())
                .with(StudentScholarInfo::getAge, studentScholar.getAge())
                .with(StudentScholarInfo::getGender, studentScholar.getGender().name())
                .with(StudentScholarInfo::getMaritalStatus, studentScholar.getMaritalStatus().name())
                .with(StudentScholarInfo::getStudentPhoneNumber, studentScholar.getStudentPhoneNumber())
                .with(StudentScholarInfo::getParentPhoneNumber, studentScholar.getParentPhoneNumber())
                .with(StudentScholarInfo::getEducationDetails, studentScholar.getEducationDetails())
                .with(StudentScholarInfo::getAddress, studentScholar.getAddress())
                .with(StudentScholarInfo::getCaste, studentScholar.getCaste().name())
                .with(StudentScholarInfo::getReligion, studentScholar.getReligion().name())
                .with(StudentScholarInfo::getNationality, studentScholar.getNationality())
                .with(StudentScholarInfo::getFatherOrMotherName, studentScholar.getFatherOrMotherName());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public Set<EducationDetail> getEducationDetails() {
        return educationDetails;
    }

    public Address getAddress() {
        return address;
    }

    public String getNationality() {return nationality; }

    public String getCaste() {return caste; }

    public String getReligion() {return religion; }

    public String getFatherOrMotherName() {
        return fatherOrMotherName;
    }
}

