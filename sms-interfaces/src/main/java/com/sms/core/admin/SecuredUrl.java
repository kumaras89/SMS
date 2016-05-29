package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.util.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_SECURED_OPERATION")
public class SecuredUrl extends BaseModel {

    @Column(name = "SO_OPERATION")
    private String operation;

    @Column(name = "SO_OPERATION_DESC")
    private String operationDesc;

    public String getOperation() {
        return operation;
    }


    public String getOperationDesc() {
        return operationDesc;
    }

    public static Builder<SecuredUrl> builder() {
        return Builder.of(SecuredUrl.class);
    }

}
