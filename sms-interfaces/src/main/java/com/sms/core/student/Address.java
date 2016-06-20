package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "SMS_TR_ADDRESS")
public class Address extends BaseModel {

    @NotNull(message = "City is empty")
    @Size(min = 1,message = "City is empty")
    @Column(name = "AD_CITY")
    private String city;

    @NotNull(message = "State is empty")
    @Size(min = 1,message = "State is Empty")
    @Pattern(regexp = "[a-zA-Z ]+", message = "State Name is invalid")
    @Column(name = "AD_STATE")
    private String state;

    @NotNull(message = "Country is empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Country Name is invalid")
    @Size(min = 2,message = "Country is invalid")
    @Column(name = "AD_COUNTRY")
    private String country;


    @Column(name = "AD_ADDRESS_2")
    private String streetName;


    @NotNull(message = "Address is empty")
    @Size(min = 1,message = "Address is empty")
    @Column(name = "AD_ADDRESS_1")
    private String doorNumber;

    @Max( value=999999, message="Postal Code is invalid")
    @Min( value=100000, message="Postal Code is invalid")
    @Column(name = "AD_POSTAL_CODE")
    private long postalCode;

    public Address() {
        super();
    }

    public static Builder<Address> builder() {
        return Builder.of(Address.class);
    }

    public static Builder toBuilder(final Address address) {
        return builder()
                .with(Address::getId, address.getId())
                .with(Address::getCity, address.getCity())
                .with(Address::getCountry, address.getCountry())
                .with(Address::getDoorNumber, address.getDoorNumber())
                .with(Address::getPostalCode, address.getPostalCode())
                .with(Address::getState, address.getState())
                .with(Address::getStreetName, address.getStreetName());
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
}