package com.sms.core.repositery;

import com.sms.core.identity.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IdCardRepository extends JpaRepository<IdCard, Long>, JpaSpecificationExecutor<IdCard> {

}
