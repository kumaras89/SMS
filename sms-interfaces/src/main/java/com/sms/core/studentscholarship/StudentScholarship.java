package com.sms.core.studentscholarship;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;
import com.sms.core.student.EducationDetail;
import com.sms.core.student.Student;
import com.sms.core.student.StudentInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Created by Assaycr-04 on 6/18/2016.
 */

public class StudentScholarship   {
    @Column(name = "SS_APPNO", unique = true)
    private Long appNo;

    @Column(name = "SS_NAME")
    private String name;

    @Column(name = "SS_DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "SS_FATHER")
    private String fatherName;

    @Column(name = "SS_GENDER")
    private String gender;

    @Column(name = "SS_NATIONALITY")
    private String nationality;

    private String religion;
    private String caste;
    private String maritalStatus;
    private Address address;
    private String phoneNumber;
    private String mailId;

    private String englishFluency;
    private Set<Language> languages;
    private Set<EducationDetail> educationDetails;
    private SslcMark sslcMarks;
    private HscMark hscMark;
    private Set<FamilyBackground> familyBackgrounds;

    public StudentScholarship() {
        super();
    }

    public static Builder<StudentScholarship> builder() {
        return Builder.of(StudentScholarship.class);
    }

    public static Builder<StudentScholarship> toBuilder(final StudentScholarshipInfo StudentScholarshipInfo) {
        return builder()
                .with(StudentScholarship::getAppNo, StudentScholarshipInfo.getAppNo())
                .with(StudentScholarship::getName, StudentScholarshipInfo.getName())
                .with(StudentScholarship::getDateOfBirth, StudentScholarshipInfo.getDateOfBirth())
                .with(StudentScholarship::getFatherName, StudentScholarshipInfo.getFatherName())
                .with(StudentScholarship::getGender, StudentScholarshipInfo.getGender())
                .with(StudentScholarship::getNationality, StudentScholarshipInfo.getNationality())
                .with(StudentScholarship::getReligion, StudentScholarshipInfo.getReligion())
                .with(StudentScholarship::getCaste, StudentScholarshipInfo.getCaste())
                .with(StudentScholarship::getMaritalStatus, StudentScholarshipInfo.getMaritalStatus())
                .with(StudentScholarship::getAddress, StudentScholarshipInfo.getAddress())
                .with(StudentScholarship::getPhoneNumber, StudentScholarshipInfo.getPhoneNumber())
                .with(StudentScholarship::getMailId, StudentScholarshipInfo.getMailId())
                .with(StudentScholarship::getEnglishFluency, StudentScholarshipInfo.getEnglishFluency())
                .with(StudentScholarship::getLanguages, StudentScholarshipInfo.getLanguages())
                .with(StudentScholarship::getEducationDetails, StudentScholarshipInfo.getEducationDetails())
                .with(StudentScholarship::getSslcMarks, StudentScholarshipInfo.getSslcMarks())
                .with(StudentScholarship::getHscMark, StudentScholarshipInfo.getHscMark())
                .with(StudentScholarship::getFamilyBackgrounds, StudentScholarshipInfo.getFamilyBackgrounds());
    }






    public void setAppNo (Long appNo){
            this.appNo = appNo;

    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public void setEnglishFluency(String englishFluency) {
        this.englishFluency = englishFluency;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public void setEducationDetails(Set<EducationDetail> educationDetails) {
        this.educationDetails = educationDetails;
    }

    public void setSslcMarks(SslcMark sslcMarks) {
        this.sslcMarks = sslcMarks;
    }

    public void setHscMark(HscMark hscMark) {
        this.hscMark = hscMark;
    }

    public void setFamilyBackgrounds(Set<FamilyBackground> familyBackgrounds) {
        this.familyBackgrounds = familyBackgrounds;
    }


    public Set<Language> getLanguages() {
        return languages;
    }

    public Long getAppNo() {
        return appNo;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getReligion() {
        return religion;
    }

    public String getCaste() {
        return caste;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }


    public String getEnglishFluency() {
        return englishFluency;
    }

    public Set<EducationDetail> getEducationDetails() {
        return educationDetails;
    }

    public SslcMark getSslcMarks() {
        return sslcMarks;
    }

    public HscMark getHscMark() {
        return hscMark;
    }

    public Set<FamilyBackground> getFamilyBackgrounds() {
        return familyBackgrounds;
    }


}
