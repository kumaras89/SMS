package com.sms.core.admin;

import com.sms.core.BaseModel;

import javax.persistence.*;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_ROLE_OPERATION_LINK")
public class RoleOperationLink extends BaseModel {

    @Column(name = "ROL_UR_ID")
    private Long userRoleId;

    @Column(name = "ROL_SO_ID")
    private Long securedOperationId;

    private RoleOperationLink(){

    }

    public RoleOperationLink(Long userId, Long securedOperationId) {
        this.userRoleId = userId;
        this.securedOperationId = securedOperationId;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public Long getSecuredOperationId() {
        return securedOperationId;
    }
}
