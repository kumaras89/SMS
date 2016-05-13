package com.sms.core.student;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "sp_tr_address")
public class Address extends BaseModel {

    @Column(name = "ad_city")
    private String city;

    @Column(name = "ad_state")
    private String state;

    @Column(name = "ad_country")
    private String country;

    @Column(name = "ad_street_name")
    private String streetName;

    @Column(name = "ad_door_number")
    private String doorNumber;

    @Column(name = "ad_postal_code")
    private long postalCode;

    public Address() {
        super();
    }

    private Address(final Builder builder) {
        super(builder);
        this.city = builder.city.get();
        this.state = builder.state.get();
        this.country = builder.country.get();
        this.streetName = builder.streetName.get();
        this.doorNumber = builder.doorNumber.get();
        this.postalCode = builder.postalCode.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Address address) {
        return builder()
                .withId(address.getId())
                .withCity(address.getCity())
                .withCountry(address.getCountry())
                .withDoorNumber(address.getDoorNumber())
                .withPostalCode(address.getPostalCode())
                .withState(address.getState())
                .withStreetName(address.getStreetName());
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public long getPostalCode() {
        return postalCode;
    }

    public static class Builder extends BaseModel.Builder<Address, Builder> {

        private Optional<String> city = Optional.empty();
        private Optional<String> state = Optional.empty();
        private Optional<String> country = Optional.empty();
        private Optional<String> streetName = Optional.empty();
        private Optional<String> doorNumber = Optional.empty();
        private Optional<Long> postalCode = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withCity(final String theCity) {
            this.city = Optional.of(theCity);
            return this;
        }

        public Builder withState(final String theState) {
            this.state = Optional.of(theState);
            return this;
        }

        public Builder withCountry(final String theCountry) {
            this.country = Optional.of(theCountry);
            return this;
        }

        public Builder withStreetName(final String theStreetName) {
            this.streetName = Optional.of(theStreetName);
            return this;
        }

        public Builder withDoorNumber(final String theDoorNumber) {
            this.doorNumber = Optional.of(theDoorNumber);
            return this;
        }

        public Builder withPostalCode(final Long thePostalCode) {
            this.postalCode = Optional.of(thePostalCode);
            return this;
        }

        public Address build() {
            return new Address(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}