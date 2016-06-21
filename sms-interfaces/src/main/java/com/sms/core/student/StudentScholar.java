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
@Table(name = "SMS_STD_SCHR")
public class StudentScholar extends BaseModel
{
    @Column(name = "ST_CODE",unique = true)
    private String code;

    @Column(name = "ST_NAME")
    private String name;

    @Column(name = "ST_DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "ST_AGE")
    private int age;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_GU_STUDENT_ID")
    private Set<Guardian> guardians;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_GENDER")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_MARITAL_STATUS")
    private MaritalStatus maritalStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_ADDRESS_ID")
    private Address address;

    @Column(name = "ST_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ST_ALTERNATE_PHONE_NUMBER")
    private String alternatePhoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_ED_STUDENT_ID")
    private Set<EducationDetail> educationDetails;

    @Column(name = "ST_NATIONALITY")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_CASTE")
    private Caste caste;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_RELIGION")
    private Religion religion;

    public static Builder<StudentScholar> builder() { return Builder.of(StudentScholar.class); }

    public static Builder<StudentScholar> toBuilder(final StudentScholarInfo studentScholarInfo) {
        return builder()
                .with(StudentScholar::getId, studentScholarInfo.getId())
                .with(StudentScholar::getCode, studentScholarInfo.getCode())
                .with(StudentScholar::getName, studentScholarInfo.getName())
                .with(StudentScholar::getDateOfBirth, studentScholarInfo.getDateOfBirth())
                .with(StudentScholar::getAge, studentScholarInfo.getAge())
                .with(StudentScholar::getGender, Gender.valueOf(studentScholarInfo.getGender()))
                .with(StudentScholar::getMaritalStatus, MaritalStatus.valueOf(studentScholarInfo.getMaritalStatus()))
                .with(StudentScholar::getPhoneNumber, studentScholarInfo.getPhoneNumber())
                .with(StudentScholar::getAlternatePhoneNumber, studentScholarInfo.getAlternatePhoneNumber())
                .with(StudentScholar::getGuardians, studentScholarInfo.getGuardians())
                .with(StudentScholar::getEducationDetails, studentScholarInfo.getEducationDetails())
                .with(StudentScholar::getAddress, studentScholarInfo.getAddress())
                .with(StudentScholar::getNationality, studentScholarInfo.getNationality())
                .with(StudentScholar::getReligion, Religion.valueOf(studentScholarInfo.getReligion()))
                .with(StudentScholar::getCaste, Caste.valueOf(studentScholarInfo.getCaste()));
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

    public Set<Guardian> getGuardians() {
        return guardians;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
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
}
