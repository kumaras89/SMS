package com.sms.core.student;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public class StudentInfo {

    private String code;
    private String name;
    private int age;
    private long phoneNumber;
    private Long alternatePhoneNumber;
    private Date dateOfBirth;
    private String mailId;
    private Address address;
    private String branchCode;
    private String gender;
    private String caste;
    private String religion;
    private Set<Guardian> guardians;

    public StudentInfo() {
    }

    private StudentInfo(final Builder builder) {
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.age = builder.age.get();
        this.phoneNumber = builder.phoneNumber.get();
        this.alternatePhoneNumber = builder.alternatePhoneNumber.get();
        this.dateOfBirth = builder.dateOfBirth.get();
        this.mailId = builder.mailId.get();
        this.address = builder.address.get();
        this.branchCode = builder.branch.get();
        this.gender = builder.gender.get();
        this.caste = builder.caste.get();
        this.religion = builder.religion.get();
        this.guardians = builder.guardians.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Student student) {
        return builder()
                .withCode(student.getCode())
                .withName(student.getName())
                .withAge(student.getAge())
                .withPhoneNumber(student.getPhoneNumber())
                .withAlternatePhoneNumber(student.getAlternatePhoneNumber())
                .withDateOfBirth(student.getDateOfBirth())
                .withMailId(student.getMailId())
                .withAddress(student.getAddress())
                .withBranchCode(student.getBranch().getCode())
                .withGender(student.getGender().name())
                .withCaste(student.getCaste().name())
                .withReligion(student.getReligion().name())
                .withGuardians(student.getGuardians());
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

    public static class Builder {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<Integer> age = Optional.empty();
        private Optional<Long> phoneNumber = Optional.empty();
        private Optional<Long> alternatePhoneNumber = Optional.empty();
        private Optional<Date> dateOfBirth = Optional.empty();
        private Optional<String> mailId = Optional.empty();
        private Optional<Address> address = Optional.empty();
        private Optional<String> branch = Optional.empty();
        private Optional<String> gender = Optional.empty();
        private Optional<String> caste = Optional.empty();
        private Optional<String> religion = Optional.empty();
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

        public Builder withBranchCode(final String theBranch) {
            this.branch = Optional.of(theBranch);
            return this;
        }

        public Builder withGender(final String theGender) {
            this.gender = Optional.of(theGender);
            return this;
        }

        public Builder withCaste(final String theCaste) {
            this.caste = Optional.of(theCaste);
            return this;
        }

        public Builder withReligion(final String theReligion) {
            this.religion = Optional.of(theReligion);
            return this;
        }

        public Builder withGuardians(final Set<Guardian> theGuardians) {
            this.guardians = Optional.of(theGuardians);
            return this;
        }

        public StudentInfo build() {
            return new StudentInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}