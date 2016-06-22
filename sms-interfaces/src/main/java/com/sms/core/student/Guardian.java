package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_TR_GUARDIAN")
public class Guardian extends BaseModel {

    private static final long serialVersionUID = -1244139837839355531L;
    @Column(name = "GU_NAME")
    private String name;

    @Column(name = "GU_OCCUPATION")
    private String occupation;

    @Column(name = "GU_RELATION")
    private Relation relationShip;

    @Column(name = "GU_PHONE_NUMBER")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public Relation getRelationShip() {
        return relationShip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Builder<Guardian> builder() {
        return Builder.of(Guardian.class);
    }
}
