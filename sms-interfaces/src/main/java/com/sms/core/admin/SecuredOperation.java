package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_SECURED_OPERATION")
public class SecuredOperation extends BaseModel {

    @Column(name = "SO_OPERATION")
    private String operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "SO_TYPE")
    private SecuredOperationType type;

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

}
