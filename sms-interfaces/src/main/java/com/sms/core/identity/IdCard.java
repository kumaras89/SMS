package com.sms.core.identity;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SMS_TR_ID_CARD")
public class IdCard extends BaseModel {

    @Column(name = "IC_UPLOADER_ID")
    private String uploaderId;

    @Column(name = "IC_UPLOADER_CATEGORY")
    private String uploaderCategory;

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

    public static Builder<IdCard> builder() {
        return Builder.of(IdCard.class);
    }

    public static Builder<IdCard> toBuilder(final IdCardInfo idCard) {
        return builder()
                .with(IdCard::getId, idCard.getId())
                .with(IdCard::getUploaderId, idCard.getUploaderId())
                .with(IdCard::getUploaderCategory, idCard.getUploaderCategory())
                .with(IdCard::getFmsId, idCard.getFmsId())
                .with(IdCard::getValidUpto, idCard.getValidUpto())
                .with(IdCard::getStatus, idCard.getStatus())
                .with(IdCard::getCreatedDate, idCard.getCreatedDate())
                .with(IdCard::getLastModifiedDate, idCard.getLastModifiedDate());
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public String getUploaderCategory() {
        return uploaderCategory;
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
}