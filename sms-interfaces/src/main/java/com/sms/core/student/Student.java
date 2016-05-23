package com.sms.core.student;

import com.sms.core.*;
import com.sms.core.branch.Branch;
import com.sms.core.course.Course;
import com.sms.core.scheme.Scheme;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Optional;
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

    public Student() {
        super();
    }

    private Student(final Builder builder) {
        super(builder);
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.age = builder.age.get();
        this.phoneNumber = builder.phoneNumber.get();
        this.alternatePhoneNumber = builder.alternatePhoneNumber.get();
        this.dateOfBirth = builder.dateOfBirth.orElseGet(Date::new);
        this.mailId = builder.mailId.get();
        this.gender = builder.gender.get();
        this.address = builder.address.get();
        this.branch = builder.branch.get();
        this.caste = builder.caste.get();
        this.religion = builder.religion.get();
        this.guardians = builder.guardians.get();
        this.educationDetails = builder.educationDetails.get();
        this.nationality= builder.nationality.get();
        this.maritalStatus = builder.maritalStatus.get();
        this.englishFluency = builder.englishFluency.get();
        this.course = builder.course.get();
        this.scheme = builder.scheme.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final StudentInfo student) {
        return builder()
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withMailId(student.getMailId())
                .withGender(Gender.valueOf(student.getGender()))
                .withCaste(Caste.valueOf(student.getCaste()))
                .withReligion(Religion.valueOf(student.getReligion()))
                .withGuardians(student.getGuardians())
                .withEducationDetails(student.getEducationDetails())
                .withAddress(student.getAddress())
                .withEnglishFluency(Rating.valueOf(student.getEnglishFluency()))
                .withMaritalStatus(MaritalStatus.valueOf(student.getMaritalStatus()))
                .withNationality(student.getNationality());
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

    public static class Builder extends BaseModel.Builder<Student, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<Integer> age = Optional.empty();
        private Optional<String> phoneNumber = Optional.empty();
        private Optional<String> alternatePhoneNumber = Optional.empty();
        private Optional<Date> dateOfBirth = Optional.empty();
        private Optional<String> mailId = Optional.empty();
        private Optional<Address> address = Optional.empty();
        private Optional<Branch> branch = Optional.empty();
        private Optional<Gender> gender = Optional.empty();
        private Optional<Caste> caste = Optional.empty();
        private Optional<Religion> religion = Optional.empty();
        private Optional<Set<Guardian>> guardians = Optional.empty();
        private Optional<Set<EducationDetail>> educationDetails = Optional.empty();
        private Optional<String> nationality = Optional.empty();
        private Optional<MaritalStatus> maritalStatus = Optional.empty();
        private Optional<Rating> englishFluency = Optional.empty();
        private Optional<Course> course = Optional.empty();
        private Optional<Scheme> scheme = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withCode(final String theCode) {
            this.code = Optional.of(theCode);
            return this;
        }

        public Builder withName(final String theName) {
            this.name = Optional.of(theName);
            return this;
        }

        public Builder withAge(final Integer theAge) {
            this.age = Optional.of(theAge);
            return this;
        }

        public Builder withPhoneNumber(final String thePhoneNumber) {
            this.phoneNumber = Optional.of(thePhoneNumber);
            return this;
        }

        public Builder withAlternatePhoneNumber(final String theAlternatePhoneNumber) {
            this.alternatePhoneNumber = Optional.ofNullable(theAlternatePhoneNumber);
            return this;
        }

        public Builder withDateOfBirth(final Date theDateOfBirth) {
            this.dateOfBirth = Optional.ofNullable(theDateOfBirth);
            return this;
        }

        public Builder withMailId(final String theMailId) {
            this.mailId = Optional.ofNullable(theMailId);
            return this;
        }

        public Builder withGender(final Gender theGender) {
            this.gender = Optional.ofNullable(theGender);
            return this;
        }

        public Builder withAddress(final Address theAddress) {
            this.address = Optional.ofNullable(theAddress);
            return this;
        }

        public Builder withBranch(final Branch theBranch) {
            this.branch = Optional.ofNullable(theBranch);
            return this;
        }

        public Builder withCaste(final Caste theCaste) {
            this.caste = Optional.ofNullable(theCaste);
            return this;
        }

        public Builder withReligion(final Religion theReligion) {
            this.religion = Optional.ofNullable(theReligion);
            return this;
        }

        public Builder withGuardians(final Set<Guardian> theGuardians) {
            this.guardians = Optional.ofNullable(theGuardians);
            return this;
        }

        public Builder withEducationDetails(final Set<EducationDetail> theEducationDetails) {
            this.educationDetails = Optional.ofNullable(theEducationDetails);
            return this;
        }

        public Builder withNationality(final String theNationality) {
            this.nationality = Optional.ofNullable(theNationality);
            return this;
        }

        public Builder withMaritalStatus(final MaritalStatus theMaritalStatus) {
            this.maritalStatus = Optional.ofNullable(theMaritalStatus);
            return this;
        }

        public Builder withEnglishFluency(final Rating theEnglishFluency) {
            this.englishFluency = Optional.ofNullable(theEnglishFluency);
            return this;
        }

        public Builder withCourse(final Course theCourse) {
            this.course = Optional.ofNullable(theCourse);
            return this;
        }

        public Builder withScheme(final Scheme theScheme) {
            this.scheme = Optional.ofNullable(theScheme);
            return this;
        }

        public Student build() {
            return new Student(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}