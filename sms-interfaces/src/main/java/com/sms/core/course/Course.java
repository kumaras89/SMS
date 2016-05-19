package com.sms.core.course;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "sp_ma_course")
public class Course extends BaseModel {

    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;

    @Column(name = "co_description")
    private String description;

    public Course() {
    }

    private Course(final Builder builder) {
        super(builder);
        this.name = builder.name.get();
        this.code = builder.code.get();
        this.description = builder.description.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final CourseInfo course) {
        return builder()
                .withName(course.getName())
                .withCode(course.getCode())
                .withDescription(course.getDescription());
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder extends BaseModel.Builder<Course, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();

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

        public Builder withDescription(final String theDescription) {
            this.description = Optional.of(theDescription);
            return this;
        }

        public Course build() {
            return new Course(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}