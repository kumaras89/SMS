package com.sms.core.scheme;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "sp_ma_scheme")
public class Scheme extends BaseModel {

    @Column(name = "sc_code", unique = true)
    private String code;

    @Column(name = "sc_name")
    private String name;

    @Column(name = "sc_description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fc_scheme_id")
    private Set<FeesCategory> feesCategories;

    public Scheme() {
        super();
    }

    public Scheme(Builder builder) {
        super(builder);
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.description = builder.description.get();
        this.feesCategories = builder.feesCategories.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final SchemeInfo schemeInfo) {
        return builder()
                .withName(schemeInfo.getName())
                .withCode(schemeInfo.getCode())
                .withDescription(schemeInfo.getDescription())
                .withFeesCategories(schemeInfo.getFeesCategories());
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

    public static class Builder extends BaseModel.Builder<Scheme, Builder> {

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

        public Scheme build() {
            return new Scheme(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
