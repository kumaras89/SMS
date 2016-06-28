package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 6/20/2016.
 */

@Entity
@Table(name = "SMS_TR_STUDENT_SCHOLARSHIP")
public class StudentScholar extends BaseModel
{
    @Column(name = "STS_APPLICATION_NUMBER",unique = true)
    private String applicationNumber;

    @Column(name = "STS_NAME")
    private String name;

    @Column(name = "STS_DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "STS_AGE")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "STS_GENDER")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "STS_MARITAL_STATUS")
    private MaritalStatus maritalStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STS_ADDRESS_ID")
    private Address address;

    @Column(name = "STS_PHONE_NUMBER")
    private String studentPhoneNumber;

    @Column(name = "STS_PARENT_PHONE_NUMBER")
    private String parentPhoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STS_ED_STUDENT_ID")
    private Set<EducationDetail> educationDetails;

    @Column(name = "STS_NATIONALITY")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "STS_CASTE")
    private Caste caste;

    @Enumerated(EnumType.STRING)
    @Column(name = "STS_RELIGION")
    private Religion religion;

    @Column(name = "STS_PARENT_NAME")
    private String fatherOrMotherName;

    @Column(name = "STS_CASTEDESCRIPTION")
    private String casteDescription;

    @Column(name = "STS_ANNUAL_INCOME")
    private String annualIncome;

    @Column(name = "STS_EMAILID" ,unique = true)
    private String emailId;

    @Column(name = "STS_CREATION_DATE")
    private Date createdDate;

    @Column(name = "STS_LAST_MODIFIED_DATE")
    private Date lastModifiedDate;


    public static Builder<StudentScholar> builder() { return Builder.of(StudentScholar.class); }

    public static Builder<StudentScholar> toBuilder(final StudentScholarInfo studentScholarInfo) {
        return builder()
                .with(StudentScholar::getId, studentScholarInfo.getId())
                .with(StudentScholar::getApplicationNumber, studentScholarInfo.getApplicationNumber())//change
                .with(StudentScholar::getName, studentScholarInfo.getName())
                .with(StudentScholar::getDateOfBirth, studentScholarInfo.getDateOfBirth())
                .with(StudentScholar::getAge, studentScholarInfo.getAge())
                .with(StudentScholar::getGender, Gender.valueOf(studentScholarInfo.getGender()))
                .with(StudentScholar::getMaritalStatus, MaritalStatus.valueOf(studentScholarInfo.getMaritalStatus()))
                .with(StudentScholar::getStudentPhoneNumber, studentScholarInfo.getStudentPhoneNumber())
                .with(StudentScholar::getParentPhoneNumber, studentScholarInfo.getParentPhoneNumber())
                .with(StudentScholar::getEducationDetails, studentScholarInfo.getEducationDetails())
                .with(StudentScholar::getAddress, studentScholarInfo.getAddress())
                .with(StudentScholar::getNationality, studentScholarInfo.getNationality())
                .with(StudentScholar::getReligion, Religion.valueOf(studentScholarInfo.getReligion()))
                .with(StudentScholar::getCaste, Caste.valueOf(studentScholarInfo.getCaste()))
                .with(StudentScholar::getFatherOrMotherName,studentScholarInfo.getFatherOrMotherName())
                .with(StudentScholar::getCasteDescription,studentScholarInfo.getCasteDescription())
                .with(StudentScholar::getAnnualIncome,studentScholarInfo.getAnnualIncome())
                .with(StudentScholar::getEmailId,studentScholarInfo.getEmailId())
                .on(StudentScholar::getLastModifiedDate).set(new Date())
                .on(StudentScholar::getCreatedDate).set(new Date());
    }

    public String getApplicationNumber() {
        return applicationNumber;
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

    public Gender getGender() {
        return gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Address getAddress() {
        return address;
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

    public String getNationality() {
        return nationality;
    }

    public Caste getCaste() {
        return caste;
    }

    public Religion getReligion() {
        return religion;
    }

    public String getFatherOrMotherName() {
        return fatherOrMotherName;
    }

    public String getCasteDescription() {
        return casteDescription;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public String getEmailId() {
        return emailId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}
