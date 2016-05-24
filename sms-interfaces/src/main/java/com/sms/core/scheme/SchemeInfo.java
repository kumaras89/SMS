package com.sms.core.scheme;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SchemeInfo {

    private String code;
    private String name;
    private String description;
    private Set<String> feesCategories;

    public SchemeInfo() {
    }

    public SchemeInfo(Builder builder) {
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.description = builder.description.get();
        this.feesCategories = builder.feesCategories.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Scheme scheme) {
        return builder()
                .withName(scheme.getName())
                .withCode(scheme.getCode())
                .withDescription(scheme.getDescription())
                .withFeesCategories(scheme.getFeesCategories().stream()
                        .map(feesCategory -> feesCategory.getCode())
                        .collect(Collectors.toSet()));
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

    public Set<String> getFeesCategories() {
        return feesCategories;
    }

    public static class Builder {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<Set<String>> feesCategories = Optional.empty();

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

        public Builder withFeesCategories(final Set<String> theFeesCategories) {
            this.feesCategories = Optional.of(theFeesCategories);
            return this;
        }

        public SchemeInfo build() {
            return new SchemeInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}
