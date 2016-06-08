package com.sms.core.repositery;

import com.sms.core.marketing.MarketingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingEmployeeRepository extends JpaRepository<MarketingEmployee, Long> {

    MarketingEmployee findByCodeIgnoreCase(final String code);

}
