package com.sms.core.repositery;

import com.sms.core.marketing.MarketingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarketingEmployeeRepository extends JpaRepository<MarketingEmployee, Long> {

    MarketingEmployee findByCodeIgnoreCase(final String code);

    @Query("select employee from MarketingEmployee employee LEFT JOIN FETCH employee.linkedUser user where user.name = :userName ")
    MarketingEmployee findByUserName(@Param("userName") final String userName);

}
