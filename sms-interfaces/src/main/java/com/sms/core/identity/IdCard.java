package com.sms.core.identity;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SMS_TR_ID_CARD")
@SequenceGenerator(name = "SMS_SQ_IC",sequenceName = "SMS_SQ_IC")
public class IdCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_IC")
    protected Long id;

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

    @Column(name = "IC_BRANCH_NAME")
    private String branchName;

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
                .with(IdCard::getAddress, idCard.getAddress())
                .on(IdCard::getBranchName).set(idCard.getBranchName());
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

    public String getBranchName() {
        return branchName;
    }

    public Long getId() {
        return id;
    }
}