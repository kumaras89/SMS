package com.sms.core.feesparticular;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SMS_MA_FEES_PARTICULAR")
public class FeesParticular extends BaseModel {

    @NotNull(message = "Fee Particular Code is empty")
    @Size(min = 1, message = "Fee Particular Code is empty")
    @Column(name = "FP_CODE", unique = true)
    private String code;

    @NotNull(message = "Fee Particular Name is empty")
    @Size(min = 1, message = "Fee Particular Name is empty")
    @Column(name = "FP_NAME")
    private String name;

    public FeesParticular() {
        super();
    }

    public static Builder<FeesParticular> builder() {
        return Builder.of(FeesParticular.class);
    }

    public static Builder<FeesParticular> toBuilder(final FeesParticular feesParticular) {
        return builder()
                .on(fp -> fp.getId()).set(feesParticular.getId())
                .on(fp -> fp.getCode()).set(feesParticular.getCode())
                .on(fp -> fp.getName()).set(feesParticular.getName());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
