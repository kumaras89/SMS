package com.sms.core.admin;

import java.util.List;

/**
 * Created by Ganesan on 27/05/16.
 */
public class RoleOperationLinkInfo {

    private Long userRoleId;
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
