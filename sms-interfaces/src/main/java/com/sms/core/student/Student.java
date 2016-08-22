package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.batch.Batch;
import com.sms.core.branch.Branch;
import com.sms.core.common.*;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.payment.Payment;
import com.sms.core.scheme.Scheme;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "SMS_TR_STUDENT")
@SequenceGenerator(name = "SMS_SQ_ST", sequenceName = "SMS_SQ_ST", allocationSize = 1)
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_ST")
    protected Long id;

    @Column(name = "ST_CODE", unique = true)
    private String code;

    @Column(name = "ST_NAME")
    private String name;

    @Column(name = "ST_SCHOLAR_APP_NO")
    private String scholarAppNo;

    @Column(name = "ST_FATHER_OR_MOTHER_NAME")
    private String fatherOrMotherName;

    @Column(name = "ST_AGE")
    private Integer age;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SP_STUDENT_ID")
    private Set<Payment> payments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_MD_SSLC_ID", unique = true)
    private SSLCMarkDetails sslcMarkDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_MD_HSC_ID", unique = true)
    private HSCMarkDetails hscMarkDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ST_ADDRESS_ID")
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


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "OL_STUDENT_ID")
    private Set<OtherLanguage> otherLanguages;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SF_STUDENT_ID")
    private Set<StudentFees> studentFees;

    @ManyToOne
    @JoinColumn(name = "ST_BATCH_ID")
    private Batch batch;

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

    @Column(name = "ST_FMS_PHOTO_ID")
    private Long fmsPhotoId;



    public Student() {
        super();
    }

    public static Builder<Student> builder() {
        return Builder.of(Student.class);
    }

    public static Builder<Student> builder(final Student student) {
        return Builder.of(Student.class, student);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
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

    public Batch getBatch() {
        return batch;
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

    public String getFatherOrMotherName() {
        return fatherOrMotherName;
    }

    public Set<OtherLanguage> getOtherLanguages() {
        return otherLanguages;
    }

    public Set<StudentFees> getStudentFees() {
        return studentFees;
    }

    public SSLCMarkDetails getSslcMarkDetails() {
        return sslcMarkDetails;
    }

    public HSCMarkDetails getHscMarkDetails() {
        return hscMarkDetails;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public Long getFmsPhotoId() {
        return fmsPhotoId;
    }

    public String getScholarAppNo() {
        return scholarAppNo;
    }

    public Long getId() {
        return id;
    }

}