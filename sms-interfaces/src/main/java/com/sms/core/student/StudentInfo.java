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
    private List<MarkDetails> markDetails;
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

    public static Builder<StudentInfo> toBuilder(final Student student) {
        return builder()
            .with(StudentInfo::getId, student.getId())
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
            .with(StudentInfo::getFeesInfos , student.getStudentFees().stream().map(FeesInfo::toBuilder).collect
                (Collectors.toList()))
            .with(StudentInfo::getNationality, student.getNationality())
            .with(StudentInfo::getBranchCode, student.getBranch().getCode())
            .with(StudentInfo::getCourseCode, student.getCourse().getCode())
            .with(StudentInfo::getSchemeCode, student.getScheme().getCode())
            .with(StudentInfo::getStatus, student.getStatus().name())
            .with(StudentInfo::getCreatedDate, student.getCreatedDate())
            .with(StudentInfo::getLastModifiedDate, student.getLastModifiedDate())
            .with(StudentInfo::getMarketingEmployeeCode, student.getMarketingEmployee().getCode());
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

    public List<MarkDetails> getMarkDetails() {
        return markDetails;
    }
}