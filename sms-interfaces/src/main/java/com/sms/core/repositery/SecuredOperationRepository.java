package com.sms.core.repositery;

import com.sms.core.admin.SecuredOperation;
import com.sms.core.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ganesan on 25/05/16.
 */
public interface SecuredOperationRepository extends JpaRepository<SecuredOperation,Long> {
}
