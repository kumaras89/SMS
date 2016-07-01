package com.sms.core.identity;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SMS_TR_ID_CARD")
public class IdCard extends BaseModel {

    @Column(name = "IC_IDENTITY_CODE")
    private String identityCode;

    @Column(name = "IC_FMS_ID")
    private Long fmsId;

    @Column(name = "IC_VALIDITY")
    @Temporal(TemporalType.DATE)
    private Date validUpto;

    @Column(name = "IC_STATUS")
    @Enumerated(EnumType.STRING)
    private IdCardStatus status;

    @Column(name = "IC_CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "IC_LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "IC_ADDRESS_ID")
    private Address address;

    @Column(name = "IC_NAME")
    private String name;

    public static Builder<IdCard> builder() {
        return Builder.of(IdCard.class);
    }

    public static Builder<IdCard> toBuilder(final IdCardInfo idCard) {
        return builder()
                .with(IdCard::getId, idCard.getId())
                .with(IdCard::getIdentityCode, idCard.getIdentityCode())
                .with(IdCard::getName, idCard.getName())
                .with(IdCard::getFmsId, idCard.getFmsId())
                .with(IdCard::getValidUpto, idCard.getValidUpto())
                .with(IdCard::getStatus, IdCardStatus.valueOf(idCard.getStatus()))
                .with(IdCard::getCreatedDate, idCard.getCreatedDate())
                .with(IdCard::getLastModifiedDate, idCard.getLastModifiedDate())
                .with(IdCard::getAddress, idCard.getAddress());
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public String getName() {
        return name;
    }

    public Long getFmsId() {
        return fmsId;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public IdCardStatus getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Address getAddress() {
        return address;
    }
}