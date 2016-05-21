package com.sms.core.scheme;

import java.util.Optional;
import java.util.Set;

public class SchemeInfo {

    private String code;
    private String name;
    private String description;
    private Set<FeesCategory> feesCategories;

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
                .withFeesCategories(scheme.getFeesCategories());
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

    public Set<FeesCategory> getFeesCategories() {
        return feesCategories;
    }

    public static class Builder {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<Set<FeesCategory>> feesCategories = Optional.empty();

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

        public Builder withFeesCategories(final Set<FeesCategory> theFeesCategories) {
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
