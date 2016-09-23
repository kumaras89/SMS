package com.sms.core.repositery;

import com.sms.core.common.Designation;
import com.sms.core.marketing.MarketingCommissionSplitConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 18:34.
 *  
 */
@SuppressWarnings("InterfaceNeverImplemented")
public interface MarketingCommissionSplitConfigRepository extends JpaRepository<MarketingCommissionSplitConfig,Long> {

    List<MarketingCommissionSplitConfig> findBySubOrdinate(final Designation subOrdinate);
}
