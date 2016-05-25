package com.sms.core.scheme;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "SMS_MA_SCHEME")
public class Scheme extends BaseModel {

    @Column(name = "SC_CODE", unique = true)
    private String code;

    @Column(name = "SC_NAME")
    private String name;

    @Column(name = "SC_DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FC_SCHEME_ID")
    private Set<SchemeFees> schemeFees;

    public Scheme() {
        super();
    }

    public Scheme(Builder builder) {
        this.id = builder.id.get();
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.description = builder.description.get();
        this.schemeFees = builder.schemeFees.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final SchemeInfo schemeInfo) {
        return builder()
                .withName(schemeInfo.getName())
                .withCode(schemeInfo.getCode())
                .withDescription(schemeInfo.getDescription());
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

    public Set<SchemeFees> getSchemeFees() {
        return schemeFees;
    }

    public static class Builder {

        private Optional<Long> id = Optional.empty();
        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<Set<SchemeFees>> schemeFees = Optional.empty();

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

        public Builder withSchemeFees(final Set<SchemeFees> theSchemeFees) {
            this.schemeFees = Optional.of(theSchemeFees);
            return this;
        }

        public Scheme build() {
            return new Scheme(this);
        }


    }
}
