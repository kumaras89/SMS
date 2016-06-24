package com.sms.core.student;

import com.sms.core.common.Builder;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 6/20/2016.
 */
public class StudentScholarInfo
{
    private Long id;

    @NotNull(message = "Scholar code is empty")
    @Size(min = 1, message = "Scholar code is empty")
    private String code;

    @NotNull(message = "Scholar name is empty")
    @Size(min = 1, message = "Scholar name is empty")
    private String name;


    @Past(message = "Date of Birth is invalid")
    @NotNull(message = "Date of Birth is empty")
    private Date dateOfBirth;

    @Min(value = 1, message = "Age is empty")
    private int age;

    @NotNull(message = "Scholar gender is empty")
    @Size(min = 1, message = "Scholar gender is empty")
    private String gender;

    @NotNull(message = "Marital Status is empty")
    @Size(min = 1, message = "Marital Status is empty")
    private String maritalStatus;

    @NotNull(message = "Student Phone number is empty")
    @Size(min = 1, message = "Student Phone number is empty")
    private String studentPhoneNumber;

    @NotNull(message = "Parent Phone number is empty")
    @Size(min = 10,max=13, message = "parent Phone number is empty")
    private String parentPhoneNumber;

    @NotNull(message = "Parent name is empty")
    private String fatherOrMotherName;


    @NotNull(message = "Nationality is empty")
    @Size(min = 1, message = "Nationality is empty")
    private String nationality;


    @NotNull(message = "Community is empty")
    @Size(min = 1, message = "Community is empty")
    private String caste;

    @NotNull(message = "Caste is empty")
    @Size(min = 1, message = "Caste is empty")
    private String castee;//Added newly


    @NotNull(message = "Religion is empty")
    @Size(min = 1, message = "Religion is empty")
    private String religion;

    @Valid
    @NotNull
    private Set<EducationDetail> educationDetails;

    @Valid
    @NotNull
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
                .with(StudentScholarInfo::getFatherOrMotherName, studentScholar.getFatherOrMotherName())
                .with(StudentScholarInfo::getCastee,studentScholar.getCastee());
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

    public String getCastee() {
        return castee;
    }
}

