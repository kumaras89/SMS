package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.Builder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Ganesan on 25/05/16.
 */
@Entity
@Table(name = "SMS_MA_ROLE_OPERATION_LINK")
public class RoleOperationLink extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "ROL_UR_ID")
    @Cascade(value = CascadeType.ALL)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "ROL_SO_ID")
    @Cascade(value = CascadeType.ALL)
    private SecuredOperation securedOperation;

    public UserRole getUserRole() {
        return userRole;
    }

    public SecuredOperation getSecuredOperation() {
        return securedOperation;
    }

    public static Builder<SecuredOperation> builder() {
        return Builder.of(SecuredOperation.class);
    }

}
