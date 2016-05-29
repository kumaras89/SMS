package com.sms.core.admin;

import com.sms.core.BaseServiceImpl;
import com.sms.core.repositery.SecuredOperationRepository;
import com.sms.core.repositery.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ganesan on 25/05/16.
 */
@Service
public class SecuredOperationServiceImpl extends BaseServiceImpl<SecuredOperation> {


    @Autowired
    public SecuredOperationServiceImpl(SecuredOperationRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected SecuredOperation buildToPersistObject(Long id, SecuredOperation entityObject) {
        return SecuredOperation.builder()
                .on(u -> u.getId()).set(id)
                .on(u -> u.getType()).set(entityObject.getType())
                .on(u -> u.getOperation()).set(entityObject.getOperation())
                .on(u -> u.getOperationDesc()).set(entityObject.getOperationDesc())
                .build();
    }
}
