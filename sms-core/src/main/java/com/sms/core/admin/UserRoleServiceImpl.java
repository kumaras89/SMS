package com.sms.core.admin;

import com.sms.core.BaseServiceImpl;
import com.sms.core.Builder;
import com.sms.core.branch.Branch;
import com.sms.core.repositery.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Ganesan on 25/05/16.
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> {


    @Autowired
    public UserRoleServiceImpl(UserRoleRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected UserRole buildToPersistObject(Long id, UserRole entityObject) {
        return UserRole.builder()
                .on(u -> u.getId()).set(id)
                .on(u -> u.getName()).set(entityObject.getName())
                .on(u -> u.getDesc()).set(entityObject.getDesc())
                .build();
    }
}
