package com.sms.core.repositery;

import com.sms.core.feesparticular.FeesParticular;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeesParticularRepository extends JpaRepository<FeesParticular, Long> {

    FeesParticular findByCodeIgnoreCase(final String code);
}
