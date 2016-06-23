package com.sms.core.admin;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Ganesan on 27/05/16.
 */
public class RoleOperationLinkInfo {

    @NotNull(message = "User role is empty")
    @Min(value=1, message = "User Role Id is empty")
    private Long userRoleId;

    @NotNull(message = "Linked Operations is empty")
    private List<Long> linkedOperations;

    public RoleOperationLinkInfo(){

    }
    public RoleOperationLinkInfo(Long userRoleId, List<Long> linkedOperations ) {
        this.userRoleId = userRoleId;
        this.linkedOperations = linkedOperations;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public List<Long> getLinkedOperations() {
        return linkedOperations;
    }

}
