package com.sms.core.admin;

import java.util.List;

/**
 * Created by Ganesan on 27/05/16.
 */
public interface RoleOperationLinkService {

    List<RoleOperationLinkInfo> getAll();

    void save(RoleOperationLinkInfo roleOperationLinkInfo);
}
