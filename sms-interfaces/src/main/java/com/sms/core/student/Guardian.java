package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "SMS_TR_GUARDIAN")
@SequenceGenerator(sequenceName = "SMS_SQ_GU",name = "SMS_SQ_GU", allocationSize = 1)
public class Guardian implements Serializable {

    private static final long serialVersionUID = -1244139837839355531L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_GU")
    protected Long id;


    @NotNull(message = "Guardian name is empty")
    @Size(min = 1,message = "Guardian name is empty")
    @Column(name = "GU_NAME")
    private String name;

    @NotNull(message = "Guardian occupation is empty")
    @Size(min = 1,message = "Guardian occupation is empty")
    @Column(name = "GU_OCCUPATION")
    private String occupation;

    @NotNull(message = "Relation is empty")
    @Column(name = "GU_RELATION")
    private Relation relationShip;

    @NotNull(message = "Phone number is empty")
    @Size(min=10,max=13, message = "Phone number is invalid")
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

    public Long getId() {
        return id;
    }
}
