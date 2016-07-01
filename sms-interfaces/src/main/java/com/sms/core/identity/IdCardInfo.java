package com.sms.core.identity;

import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import java.util.Date;

public class IdCardInfo {

    private Long id;
    private String identityCode;
    private Long fmsId;
    private Date validUpto;
    private String status;
    private Date createdDate;
    private Date lastModifiedDate;
    private Address address;
    private String name;
    private String branchName;

    public static Builder<IdCardInfo> builder() {
        return Builder.of(IdCardInfo.class);
    }

    public static Builder<IdCardInfo> toBuilder(final IdCard idCard) {
        return builder()
                .with(IdCardInfo::getId, idCard.getId())
                .with(IdCardInfo::getIdentityCode, idCard.getIdentityCode())
                .with(IdCardInfo::getName, idCard.getName())
                .with(IdCardInfo::getFmsId, idCard.getFmsId())
                .with(IdCardInfo::getValidUpto, idCard.getValidUpto())
                .with(IdCardInfo::getStatus, idCard.getStatus().name())
                .with(IdCardInfo::getCreatedDate, idCard.getCreatedDate())
                .with(IdCardInfo::getLastModifiedDate, idCard.getLastModifiedDate())
                .with(IdCardInfo::getAddress, idCard.getAddress())
                .on(IdCardInfo::getBranchName).set(idCard.getBranchName());
    }

    public static IdCardInfo build(final IdCard idCard) {
        return toBuilder(idCard).build();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public Address getAddress() {
        return address;
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

    public String getBranchName() {
        return branchName;
    }
}