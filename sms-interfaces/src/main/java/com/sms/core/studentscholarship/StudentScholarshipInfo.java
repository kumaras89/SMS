package com.sms.core.studentscholarship;

import com.sms.core.student.Address;
import com.sms.core.student.EducationDetail;
import com.sms.core.student.Guardian;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Assaycr-04 on 6/18/2016.
 */
public class StudentScholarshipInfo {

    private Long id;
    private Long appNo;
    private String name;
    private Date dateOfBirth;
    private String fatherName;
    private String gender;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppNo() {
        return appNo;
    }

    public void setAppNo(Long appNo) {
        this.appNo = appNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }


    public String getEnglishFluency() {
        return englishFluency;
    }

    public void setEnglishFluency(String englishFluency) {
        this.englishFluency = englishFluency;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<EducationDetail> getEducationDetails() {
        return educationDetails;
    }

    public void setEducationDetails(Set<EducationDetail> educationDetails) {
        this.educationDetails = educationDetails;
    }

    public SslcMark getSslcMarks() {
        return sslcMarks;
    }

    public void setSslcMarks(SslcMark sslcMarks) {
        this.sslcMarks = sslcMarks;
    }

    public HscMark getHscMark() {
        return hscMark;
    }

    public void setHscMark(HscMark hscMark) {
        this.hscMark = hscMark;
    }

    public Set<FamilyBackground> getFamilyBackgrounds() {
        return familyBackgrounds;
    }

    public void setFamilyBackgrounds(Set<FamilyBackground> familyBackgrounds) {
        this.familyBackgrounds = familyBackgrounds;
    }



}
