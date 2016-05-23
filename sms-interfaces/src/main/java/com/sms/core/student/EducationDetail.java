package com.sms.core.student;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "SMS_TR_EDUCATION_DETAIL")
public class EducationDetail extends BaseModel {

    @Column(name = "ED_EXAM_PASSED")
    private String examPassed;

    @Column(name = "ED_INSTITUTE_NAME")
    private String instituteName;

    @Column(name = "ED_GROUP_NAME")
    private String groupName;

    @Column(name = "ED_PASSING_YEAR")
    private Long passingYear;

    @Column(name = "ED_PERCENTAGE_OBTAINED")
    private Integer percentageObtained;

    @Column(name = "ED_REMARK")
    private String remark;

    public EducationDetail() {
    }

    private EducationDetail(final Builder builder) {
        super(builder);
        this.examPassed = builder.examPassed.get();
        this.instituteName = builder.instituteName.get();
        this.groupName = builder.groupName.get();
        this.passingYear = builder.passingYear.get();
        this.percentageObtained = builder.percentageObtained.get();
        this.remark = builder.remark.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getInstituteName() {
        return instituteName;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getPercentageObtained() {
        return percentageObtained;
    }

    public String getRemark() {
        return remark;
    }

    public String getExamPassed() {
        return examPassed;
    }

    public Long getPassingYear() {
        return passingYear;
    }

    public static class Builder extends BaseModel.Builder<EducationDetail, Builder> {

        private Optional<String> examPassed = Optional.empty();
        private Optional<String> instituteName = Optional.empty();
        private Optional<String> groupName = Optional.empty();
        private Optional<Long> passingYear = Optional.empty();
        private Optional<Integer> percentageObtained = Optional.empty();
        private Optional<String> remark = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withExamPassed(final String theExamPassed) {
            this.examPassed = Optional.ofNullable(theExamPassed);
            return this;
        }

        public Builder withInstituteName(final String theInstituteName) {
            this.instituteName = Optional.ofNullable(theInstituteName);
            return this;
        }

        public Builder withGroupName(final String theGroupName) {
            this.groupName = Optional.ofNullable(theGroupName);
            return this;
        }

        public Builder withPassingYear(final Long thePassingYear) {
            this.passingYear = Optional.ofNullable(thePassingYear);
            return this;
        }

        public Builder withPercentageObtained(final Integer thePercentageObtained) {
            this.percentageObtained = Optional.ofNullable(thePercentageObtained);
            return this;
        }

        public Builder withRemark(final String theRemark) {
            this.remark = Optional.ofNullable(theRemark);
            return this;
        }

        public EducationDetail build() {
            return new EducationDetail(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
