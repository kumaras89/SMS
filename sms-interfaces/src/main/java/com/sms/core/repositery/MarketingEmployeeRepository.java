package com.sms.core.repositery;

import com.sms.core.common.Designation;
import com.sms.core.marketing.MarketingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("InterfaceNeverImplemented")
public interface MarketingEmployeeRepository extends JpaRepository<MarketingEmployee, Long> {

    /**
     *
     * @param code
     * @return
     */
    MarketingEmployee findByCodeIgnoreCase(final String code);

    /**
     *
     * @param userName
     * @return
     */
    @Query("select employee from MarketingEmployee employee LEFT JOIN FETCH employee.linkedUser user where user.name = :userName ")
    MarketingEmployee findByUserName(@Param("userName") final String userName);

    /**
     *
     * @param designation
     * @return
     */
    List<MarketingEmployee> findByDesignation(final Designation designation);

}
