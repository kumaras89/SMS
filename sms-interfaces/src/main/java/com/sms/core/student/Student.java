package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.Caste;
import com.sms.core.Gender;
import com.sms.core.Religion;
import com.sms.core.branch.Branch;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "sp_tr_student")
public class Student extends BaseModel {

    @Column(name = "st_code")
    private String code;

    @Column(name = "st_name")
    private String name;

    @Column(name = "st_age")
    private int age;

    @Column(name = "st_phone_number")
    private long phoneNumber;

    @Column(name = "st_alternate_phone_number")
    private Long alternatePhoneNumber;

    @Column(name = "st_date_of_birth")
    private Date dateOfBirth;

    @Column(name = "st_mail_id")
    private String mailId;

    @Column(name = "st_gender")
    private Gender gender;

    @Column(name = "st_caste")
    private Caste caste;

    @Column(name = "st_religion")
    private Religion religion;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gu_student_id")
    private Set<Guardian> guardians;

    @ManyToOne
    @JoinColumn(name = "st_address_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "st_branch_id")
    private Branch branch;

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
                .withAddress(student.getAddress());
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Long getAlternatePhoneNumber() {
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

    public static class Builder extends BaseModel.Builder<Student, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<Integer> age = Optional.empty();
        private Optional<Long> phoneNumber = Optional.empty();
        private Optional<Long> alternatePhoneNumber = Optional.empty();
        private Optional<Date> dateOfBirth = Optional.empty();
        private Optional<String> mailId = Optional.empty();
        private Optional<Address> address = Optional.empty();
        private Optional<Branch> branch = Optional.empty();
        private Optional<Gender> gender = Optional.empty();
        private Optional<Caste> caste = Optional.empty();
        private Optional<Religion> religion = Optional.empty();
        private Optional<Set<Guardian>> guardians = Optional.empty();


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

        public Builder withPhoneNumber(final Long thePhoneNumber) {
            this.phoneNumber = Optional.of(thePhoneNumber);
            return this;
        }

        public Builder withAlternatePhoneNumber(final Long theAlternatePhoneNumber) {
            this.alternatePhoneNumber = Optional.of(theAlternatePhoneNumber);
            return this;
        }

        public Builder withDateOfBirth(final Date theDateOfBirth) {
            this.dateOfBirth = Optional.ofNullable(theDateOfBirth);
            return this;
        }

        public Builder withMailId(final String theMailId) {
            this.mailId = Optional.of(theMailId);
            return this;
        }

        public Builder withGender(final Gender theGender) {
            this.gender = Optional.of(theGender);
            return this;
        }

        public Builder withAddress(final Address theAddress) {
            this.address = Optional.of(theAddress);
            return this;
        }

        public Builder withBranch(final Branch theBranch) {
            this.branch = Optional.of(theBranch);
            return this;
        }

        public Builder withCaste(final Caste theCaste) {
            this.caste = Optional.of(theCaste);
            return this;
        }

        public Builder withReligion(final Religion theReligion) {
            this.religion = Optional.of(theReligion);
            return this;
        }

        public Builder withGuardians(final Set<Guardian> theGuardians) {
            this.guardians = Optional.of(theGuardians);
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