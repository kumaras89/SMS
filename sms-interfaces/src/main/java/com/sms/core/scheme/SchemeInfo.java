package com.sms.core.scheme;

import com.sms.core.common.Builder;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class SchemeInfo {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Set<FeesInfo> feesInfos;
    private BigDecimal feesAmount;

    public SchemeInfo() {
    }

    public static Builder<SchemeInfo> builder() {
        return Builder.of(SchemeInfo.class);
    }

    public static Builder<SchemeInfo> toBuilder(final Scheme scheme) {
        return builder()
                .with(SchemeInfo::getId, scheme.getId())
                .with(SchemeInfo::getName, scheme.getName())
                .with(SchemeInfo::getCode, scheme.getCode())
                .with(SchemeInfo::getDescription, scheme.getDescription())
                .with(SchemeInfo::getFeesAmount, scheme.getFeesAmount())
                .with(SchemeInfo::getFeesInfos, scheme.getSchemeFees().stream()
                        .map(feesCategory -> FeesInfo.toBuilder(feesCategory).build())
                        .collect(Collectors.toSet()));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Set<FeesInfo> getFeesInfos() {
        return feesInfos;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }
}
