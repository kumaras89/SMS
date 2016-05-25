package com.sms.core.repositery;

import com.sms.core.admin.User;
import com.sms.core.admin.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ganesan on 25/05/16.
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findByName(String name);

}
