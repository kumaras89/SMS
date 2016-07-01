package com.sms.core.student;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
@Table(name = "SMS_TR_EDUCATION_DETAIL")
public class EducationDetail extends BaseModel {

    private static final long serialVersionUID = 533778750992658222L;
    @NotNull(message = "Exam Passed  is empty")
    @Size(min = 1, message = "Exam Passed is empty")
    @Column(name = "ED_EXAM_PASSED")
    private String examPassed;

    @NotNull(message = "Institute Name  is empty")
    @Size(min = 1, message = "Institute Name is empty")
    @Column(name = "ED_INSTITUTE_NAME")
    private String instituteName;

    @NotNull(message = "Group Name  is empty")
    @Size(min = 1, message = "Group Name is empty")
    @Column(name = "ED_GROUP_NAME")
    private String groupName;


    @Min(value = 2000,message = "Passing year is invalid")
    @Max(value = 2200,message = "Passing year is invalid")
    @Column(name = "ED_PASSING_YEAR")
    private Long passingYear;


    @Min(value = 1, message = "Percentage is empty")
    @Max(value = 100, message = "Percentage is invalid")
    @Column(name = "ED_PERCENTAGE_OBTAINED")
    private Integer percentageObtained;


    @Column(name = "ED_REMARK")
    private String remark;

    public EducationDetail() {
    }

    private EducationDetail(final Builder builder) {
        this.id = builder.id.get();
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

    public static class Builder  {

        private Optional<Long> id = Optional.empty();
        private Optional<String> examPassed = Optional.empty();
        private Optional<String> instituteName = Optional.empty();
        private Optional<String> groupName = Optional.empty();
        private Optional<Long> passingYear = Optional.empty();
        private Optional<Integer> percentageObtained = Optional.empty();
        private Optional<String> remark = Optional.empty();

        private Builder() {
            super();
        }

        public Builder witId(final Long theId) {
            this.id = Optional.ofNullable(theId);
            return this;
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


    }
}
