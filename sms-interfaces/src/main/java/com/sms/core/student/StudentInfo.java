package com.sms.core.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.core.common.Builder;
import com.sms.core.feesparticular.FeesInfo;

import javax.persistence.MapsId;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInfo {

    private Long id;


    private String code;

    @NotNull(message = "Student name is empty")
    @Size(min = 1, message = "Student name is empty")
    private String name;

    @NotNull(message = "Date of Birth is empty")
    @Past(message = "Check Date of Birth")
    private Date dateOfBirth;

    @Min(value = 1, message = "Age is empty")
    private int age;

    @NotNull(message = "Parent name is empty")
    @Size(min = 1, message = "Parent name is empty")
    private String fatherOrMotherName;

    @NotNull(message = "Gender is empty")
    @Size(min = 1, message = "Gender is empty")
    private String gender;

    @NotNull(message = "Nationality is empty")
    @Size(min = 1, message = "Nationality is empty")
    private String nationality;

    @NotNull(message = "Religion is empty")
    @Size(min = 1, message = "Religion  is empty")
    private String religion;

    @NotNull(message = "Caste is empty")
    @Size(min = 1, message = "Caste is empty")
    private String caste;

    @NotNull(message = "Marital Status is empty")
    @Size(min = 1, message = "Marital Status is empty")
    private String maritalStatus;

    @Valid
    @NotNull(message = "Address is empty")
    private Address address;

    @NotNull(message = "Mail id  is empty")
    @Size(min = 1, message = "Mail id is empty")
    private String mailId;

    @NotNull(message = "English empty")
    @Size(min = 1, message = "Caste is empty")
    private String englishFluency;

    @NotNull(message = "Language is empty")
    @Valid
    private Set<OtherLanguage> otherLanguages;

    @NotNull(message = "SSLC Mark Details is empty")
    @Valid
    private SSLCMarkDetails sslcMarkDetails;

    @NotNull(message = "HSC Mark Details is empty")
    @Valid
    private HSCMarkDetails hscMarkDetails;

    @NotNull(message = "Phone number is empty")
    @Size(min = 10, max = 13,message = "Invalid Phone number")
    private String phoneNumber;

    @NotNull(message = "Alternate Phone number is empty")
    @Size(min = 10, max = 13,message = "Invalid Alternative Phone number")
    private String alternatePhoneNumber;


    @Valid
    @NotNull(message = "Educational Details is empty")
    private Set<EducationDetail> educationDetails;

    @Valid
    @NotNull(message = "Guardian List is empty")
    private Set<Guardian> guardians;


    private List<FeesInfo> feesInfos;

    @NotNull(message = "Branch is empty")
    @Size(min = 1, message = "Branch code is empty")
    private String branchCode;

    @NotNull(message = "Course is empty")
    @Size(min = 1, message = "Course code is empty")
    private String courseCode;

    @NotNull(message = "Scheme is empty")
    @Size(min = 1, message = "Scheme code is empty")
    private String schemeCode;

    private String status;


    private Date createdDate;

    private Date lastModifiedDate;

    @NotNull(message = "Marketing employee code is empty")
    @Size(min = 1, message = "Marketing employee code is empty")
    private String marketingEmployeeCode;

    public StudentInfo() {
    }

    public static Builder<StudentInfo> builder() {
        return Builder.of(StudentInfo.class);
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

    public String getMarketingEmployeeCode() {
        return marketingEmployeeCode;
    }

    public String getFatherOrMotherName() {
        return fatherOrMotherName;
    }

    public Set<OtherLanguage> getOtherLanguages() {
        return otherLanguages;
    }

    public List<FeesInfo> getFeesInfos() {
        return feesInfos;
    }

    public SSLCMarkDetails getSslcMarkDetails() {
        return sslcMarkDetails;
    }

    public HSCMarkDetails getHscMarkDetails() {
        return hscMarkDetails;
    }
}