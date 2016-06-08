package com.sms.core.student;

import com.sms.core.*;
import com.sms.core.branch.Branch;
import com.sms.core.common.*;
import com.sms.core.course.Course;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.scheme.Scheme;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "SMS_TR_STUDENT")
public class Student extends BaseModel {

    @Column(name = "ST_CODE", unique = true)
    private String code;

    @Column(name = "ST_NAME")
    private String name;

    @Column(name = "ST_AGE")
    private int age;

    @Column(name = "ST_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ST_ALTERNATE_PHONE_NUMBER")
    private String alternatePhoneNumber;

    @Column(name = "ST_DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "ST_MAIL_ID", unique = true)
    private String mailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_GENDER")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_CASTE")
    private Caste caste;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_RELIGION")
    private Religion religion;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "GU_STUDENT_ID")
    private Set<Guardian> guardians;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ED_STUDENT_ID")
    private Set<EducationDetail> educationDetails;

    @ManyToOne
    @JoinColumn(name = "ST_ADDRESS_ID")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "ST_BRANCH_ID")
    private Branch branch;

    @Column(name = "ST_NATIONALITY")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_MARITAL_STATUS")
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_ENGLISH_FLUENCY")
    private Rating englishFluency;

    @ManyToOne
    @JoinColumn(name = "ST_COURSE_ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "ST_SCHEME_ID")
    private Scheme scheme;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_STATUS")
    private StudentStatus status;

    @Column(name = "ST_CREATION_DATE")
    private Date createdDate;

    @Column(name = "ST_LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "ST_MARKETING_EMPLOYEE_ID")
    private MarketingEmployee marketingEmployee;

    public Student() {
        super();
    }

    public static Builder<Student> builder() {
        return Builder.of(Student.class);
    }

    public static Builder<Student> toBuilder(final StudentInfo studentInfo) {
        return builder()
                .with(Student::getName, studentInfo.getName())
                .with(Student::getAge, studentInfo.getAge())
                .with(Student::getPhoneNumber, studentInfo.getPhoneNumber())
                .with(Student::getAlternatePhoneNumber, studentInfo.getAlternatePhoneNumber())
                .with(Student::getDateOfBirth, studentInfo.getDateOfBirth())
                .with(Student::getMailId, studentInfo.getMailId())
                .with(Student::getGender, Gender.valueOf(studentInfo.getGender()))
                .with(Student::getCaste, Caste.valueOf(studentInfo.getCaste()))
                .with(Student::getReligion, Religion.valueOf(studentInfo.getReligion()))
                .with(Student::getGuardians, studentInfo.getGuardians())
                .with(Student::getEducationDetails, studentInfo.getEducationDetails())
                .with(Student::getAddress, studentInfo.getAddress())
                .with(Student::getEnglishFluency, Rating.valueOf(studentInfo.getEnglishFluency()))
                .with(Student::getMaritalStatus, MaritalStatus.valueOf(studentInfo.getMaritalStatus()))
                .with(Student::getNationality, studentInfo.getNationality())
                .with(Student::getStatus, StudentStatus.valueOf(studentInfo.getStatus()))
                .with(Student::getCreatedDate, studentInfo.getCreatedDate())
                .with(Student::getLastModifiedDate, studentInfo.getLastModifiedDate());
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

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public Branch getBranch() {
        return branch;
    }

    public Caste getCaste() {
        return caste;
    }

    public Religion getReligion() {
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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Rating getEnglishFluency() {
        return englishFluency;
    }

    public Course getCourse() {
        return course;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public MarketingEmployee getMarketingEmployee() {
        return marketingEmployee;
    }
}