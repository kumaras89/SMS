package com.sms.core.scheme;

import java.util.Optional;

public class FeesCategoryInfo {

    private String code;
    private String name;
    private int weightage = 0;

    public FeesCategoryInfo() {
    }

    public FeesCategoryInfo(Builder builder) {
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.weightage = builder.weightage.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final FeesCategory feesCategory) {
        return builder()
                .withCode(feesCategory.getCode())
                .withName(feesCategory.getName())
                .withWeightage(feesCategory.getWeightage());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getWeightage() {
        return weightage;
    }

    public static class Builder {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<Integer> weightage = Optional.empty();

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

        public Builder withWeightage(final Integer theWeightage) {
            this.weightage = Optional.of(theWeightage);
            return this;
        }

        public FeesCategoryInfo build() {
            return new FeesCategoryInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}
