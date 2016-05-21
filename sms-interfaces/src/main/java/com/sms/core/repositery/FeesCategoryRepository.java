package com.sms.core.repositery;

import com.sms.core.scheme.FeesCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeesCategoryRepository extends JpaRepository<FeesCategory, Long> {

    FeesCategory findByCodeIgnoreCase(final String code);
}
