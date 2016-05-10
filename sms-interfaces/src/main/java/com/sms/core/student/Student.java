package com.sms.core.student;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sms.core.BaseModel;

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

    @ManyToOne
    @JoinColumn(name = "st_address_id")
//    @Cascade(value = CascadeType.ALL)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "st_branch_id")
//    @Cascade(value = CascadeType.ALL)
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
        this.dateOfBirth = builder.dateOfBirth.get();
        this.mailId = builder.mailId.get();
        this.address = builder.address.get();
        this.branch = builder.branch.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Student student) {
        return builder()
                .withId(student.getId())
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withMailId(student.getMailId())
                .withAddress(student.getAddress())
                .withBranch(student.getBranch());
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

    public Address getAddress() {
        return address;
    }

    public Branch getBranch() {
        return branch;
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
            this.dateOfBirth = Optional.of(theDateOfBirth);
            return this;
        }

        public Builder withMailId(final String theMailId) {
            this.mailId = Optional.of(theMailId);
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

        public Student build() {
            return new Student(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}