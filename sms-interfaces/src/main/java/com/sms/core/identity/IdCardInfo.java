package com.sms.core.identity;

import com.sms.core.common.Builder;

import java.util.Date;

public class IdCardInfo {

    private Long id;
    private String uploaderId;
    private String uploaderCategory;
    private Long fmsId;
    private Date validUpto;
    private String status;
    private Date createdDate;
    private Date lastModifiedDate;

    public static Builder<IdCardInfo> builder() {
        return Builder.of(IdCardInfo.class);
    }

    public static Builder<IdCardInfo> toBuilder(final IdCard idCard) {
        return builder()
                .with(IdCardInfo::getId, idCard.getId())
                .with(IdCardInfo::getUploaderId, idCard.getUploaderId())
                .with(IdCardInfo::getUploaderCategory, idCard.getUploaderCategory())
                .with(IdCardInfo::getFmsId, idCard.getFmsId())
                .with(IdCardInfo::getValidUpto, idCard.getValidUpto())
                .with(IdCardInfo::getStatus, idCard.getStatus())
                .with(IdCardInfo::getCreatedDate, idCard.getCreatedDate())
                .with(IdCardInfo::getLastModifiedDate, idCard.getLastModifiedDate());
    }

    public Long getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}