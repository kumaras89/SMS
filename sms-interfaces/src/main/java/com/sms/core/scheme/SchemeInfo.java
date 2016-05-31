package com.sms.core.scheme;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SchemeInfo {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Set<SchemeFeesInfo> schemeFeesInfos;
    private BigDecimal feesAmount;

    public SchemeInfo() {
    }

    public SchemeInfo(Builder builder) {
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.description = builder.description.get();
        this.schemeFeesInfos = builder.schemeFeesInfos.get();
        this.id = builder.id.get();
        this.feesAmount = builder.feesAmount.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Scheme scheme) {
        return builder()
                .withId(scheme.getId())
                .withName(scheme.getName())
                .withCode(scheme.getCode())
                .withDescription(scheme.getDescription())
                .withFeesAmount(scheme.getFeesAmount())
                .withFeesCategories(scheme.getSchemeFees().stream()
                        .map(feesCategory -> SchemeFeesInfo.toBuilder(feesCategory).build())
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

    public Long getId() {
        return id;
    }

    public Set<SchemeFeesInfo> getSchemeFeesInfos() {
        return schemeFeesInfos;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public static class Builder {

        private Optional<Long> id = Optional.empty();
        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<Set<SchemeFeesInfo>> schemeFeesInfos = Optional.empty();
        private Optional<BigDecimal> feesAmount = Optional.empty();

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

        public Builder withFeesCategories(final Set<SchemeFeesInfo> theFeesCategories) {
            this.schemeFeesInfos = Optional.of(theFeesCategories);
            return this;
        }

        public Builder withFeesAmount(final BigDecimal thefeesAmount) {
            this.feesAmount = Optional.of(thefeesAmount);
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
