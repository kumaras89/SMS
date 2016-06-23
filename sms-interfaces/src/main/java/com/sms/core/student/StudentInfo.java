package com.sms.core.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.core.common.Builder;
import com.sms.core.common.FunctionUtils;
import com.sms.core.scheme.FeesInfo;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInfo {

    private Long id;
    private String code;
    private String name;
    private Date dateOfBirth;
    private int age;
    private String fatherOrMotherName;
    private String gender;
    private String nationality;
    private String religion;
    private String caste;
    private String maritalStatus;
    private Address address;
    private String mailId;
    private String englishFluency;
    private Set<OtherLanguage> otherLanguages;
    private MarkDetails tenthMarkDetails;
    private MarkDetails tewelthMarkDetails;
    private String phoneNumber;
    private String alternatePhoneNumber;
    private Set<EducationDetail> educationDetails;
    private Set<Guardian> guardians;
    private List<FeesInfo> feesInfos;
    private String branchCode;
    private String courseCode;
    private String schemeCode;
    private String status;
    private Date createdDate;
    private Date lastModifiedDate;
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

    public MarkDetails getTenthMarkDetails() {
        return tenthMarkDetails;
    }

    public MarkDetails getTewelthMarkDetails() {
        return tewelthMarkDetails;
    }
}