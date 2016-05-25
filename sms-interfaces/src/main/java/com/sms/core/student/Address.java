package com.sms.core.student;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "SMS_TR_ADDRESS")
public class Address extends BaseModel {

    @Column(name = "AD_CITY")
    private String city;

    @Column(name = "AD_STATE")
    private String state;

    @Column(name = "AD_COUNTRY")
    private String country;

    @Column(name = "AD_STREET_NAME")
    private String streetName;

    @Column(name = "AD_DOOR_NUMBER")
    private String doorNumber;

    @Column(name = "AD_POSTAL_CODE")
    private long postalCode;

    public Address() {
        super();
    }

    private Address(final Builder builder) {
        this.id = builder.id.get();
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

    public static class Builder {

        private Optional<Long> id = Optional.empty();
        private Optional<String> city = Optional.empty();
        private Optional<String> state = Optional.empty();
        private Optional<String> country = Optional.empty();
        private Optional<String> streetName = Optional.empty();
        private Optional<String> doorNumber = Optional.empty();
        private Optional<Long> postalCode = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withId(final Long theId) {
            this.id = Optional.of(theId);
            return this;
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


    }
}