package com.sms.core.feesparticular;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "SMS_MA_FEES_PARTICULAR")
@SequenceGenerator(name = "SMS_SQ_FP",sequenceName = "SMS_SQ_FP",allocationSize = 1)
public class FeesParticular implements Serializable {



    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_FP")
    protected Long id;

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

    public Long getId() {
        return id;
    }
}
