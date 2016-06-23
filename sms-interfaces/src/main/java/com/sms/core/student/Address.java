package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

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
    private String address1;

    @Column(name = "AD_ADDRESS_2")
    private String address2;

    @Column(name = "AD_ADDRESS_3")
    private String address3;

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

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
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

    public String getAddress3() {
        return address3;
    }
}