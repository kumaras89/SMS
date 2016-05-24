package com.sms.core.scheme;

import java.util.Optional;

public class SchemeFeesInfo {

    private Long id;
    private String feesParticularCode;
    private int weightage = 0;

    public SchemeFeesInfo() {
    }

    public SchemeFeesInfo(Builder builder) {
        this.feesParticularCode = builder.feesParticularCode.get();
        this.weightage = builder.weightage.get();
        this.id = builder.id.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final SchemeFees schemeFees) {
        return builder()
                .withId(schemeFees.getId())
                .withFeesParticularCode(schemeFees.getFeesParticular().getCode())
                .withWeightage(schemeFees.getWeightage());
    }

    public String getFeesParticularCode() {
        return feesParticularCode;
    }

    public int getWeightage() {
        return weightage;
    }

    public Long getId() {
        return id;
    }

    public static class Builder {

        private Optional<String> feesParticularCode = Optional.empty();
        private Optional<Integer> weightage = Optional.empty();
        private Optional<Long> id = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withId(final Long theId) {
            this.id = Optional.of(theId);
            return this;
        }

        public Builder withFeesParticularCode(final String theFeesParticularCode) {
            this.feesParticularCode = Optional.of(theFeesParticularCode);
            return this;
        }

        public Builder withWeightage(final Integer theWeightage) {
            this.weightage = Optional.of(theWeightage);
            return this;
        }

        public SchemeFeesInfo build() {
            return new SchemeFeesInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}
