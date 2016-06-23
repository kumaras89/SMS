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

    private static final long serialVersionUID = 8708958564726670901L;


    @NotNull(message = "door number is empty")
    @Size(min = 1,message = "door number is empty")
    @Column(name = "AD_ADDRESS_1")
    private String doorNumber;

    @Column(name = "AD_ADDRESS_2")
    private String streetName;

    @NotNull(message = "taluk is empty")
    @Size(min = 1,message = "taluk is empty")
    @Column(name = "AD_TALUK")
    private String taluk;

    @NotNull(message = "District is empty")
    @Size(min = 1,message = "District is Empty")
    @Column(name = "AD_DISTRICT")
    private String district;

    
    @Max( value=999999, message="Postal Code is invalid")
    @Min( value=100000, message="Postal Code is invalid")
    @NotNull(message = "Postal Code is Empty")
    @Column(name = "AD_POSTAL_CODE")
    private long postalCode;

    public Address() {
        super();
    }

    public static Builder<Address> builder() {
        return Builder.of(Address.class);
    }

    public static Builder<Address> toBuilder(final Address address) {
        return builder()
                .with(Address::getId, address.getId())
                .with(Address::getTaluk, address.getTaluk())
                .with(Address::getDistrict, address.getDistrict())
                .with(Address::getDoorNumber, address.getDoorNumber())
                .with(Address::getPostalCode, address.getPostalCode())
                .with(Address::getStreetName, address.getStreetName());
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getTaluk() {
        return taluk;
    }

    public String getDistrict() {
        return district;
    }

    public long getPostalCode() {
        return postalCode;
    }
}