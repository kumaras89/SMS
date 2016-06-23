package com.sms.core.course;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class CourseInfo {

    private Long id;

    @NotNull(message = "Course Code is empty")
    @Size(min = 1, message = "Course Code is empty")
    private String code;

    @NotNull(message = "Course name is empty")
    @Size(min = 1, message = "Course name is empty")
    private String name;

    @NotNull(message = "Course Description is empty")
    @Size(min = 1, message = "Course Description is empty")
    private String description;

    public CourseInfo() {
    }

    private CourseInfo(final Builder builder) {
        this.name = builder.name.get();
        this.code = builder.code.get();
        this.description = builder.description.get();
        this.id = builder.id.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Course course) {
        return builder()
                .withId(course.getId())
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

    public Long getId() {
        return id;
    }

    public static class Builder {

        private Optional<Long> id = Optional.empty();
        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withId(final Long theId) {
            this.id = Optional.of(theId);
            return this;
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

        public CourseInfo build() {
            return new CourseInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}
