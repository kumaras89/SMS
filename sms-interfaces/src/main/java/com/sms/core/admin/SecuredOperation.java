package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_SECURED_OPERATION")
@SequenceGenerator(sequenceName = "SMS_SQ_SO",name = "SMS_SQ_SO")
public class SecuredOperation implements Serializable {


    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_SO")
    protected Long id;

    @NotNull(message = "Operation  is empty")
    @Size(min = 1, message = "Operation is empty")
    @Column(name = "SO_OPERATION")
    private String operation;

    @NotNull(message = "Operation type is empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "SO_TYPE")
    private SecuredOperationType type;

    @NotNull(message = "Operation description is empty")
    @Size(min = 1, message = "Operation description is empty")
    @Column(name = "SO_OPERATION_DESC")
    private String operationDesc;

    public SecuredOperationType getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }


    public String getOperationDesc() {
        return operationDesc;
    }

    public static Builder<SecuredOperation> builder() {
        return Builder.of(SecuredOperation.class);
    }

    public Long getId() {
        return id;
    }
}
