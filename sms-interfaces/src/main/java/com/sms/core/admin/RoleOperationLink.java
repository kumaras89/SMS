package com.sms.core.admin;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_ROLE_OPERATION_LINK")
@SequenceGenerator(sequenceName = "SMS_SQ_ROL",name = "SMS_SQ_ROL", allocationSize = 1)
public class RoleOperationLink implements Serializable {


    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_ROL")
    protected Long id;


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

    public Long getId() {
        return id;
    }
}
