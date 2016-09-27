package com.sms.core.marketing;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 9/26/2016.
 */
public interface MarketingSplitConfigService {
    /**
     * @return
     */
    List<MarketingCommissionConfigSplitInfo> findAll();

    /**
     * @param entityType
     * @return
     */

    Optional<MarketingCommissionConfigSplitInfo> save(final MarketingCommissionConfigSplitInfo entityType);

    /**
     * @param id
     * @param marketingCommissionConfigSplitInfo
     * @return
     */

    Optional<MarketingCommissionConfigSplitInfo> update(final long id, final MarketingCommissionConfigSplitInfo marketingCommissionConfigSplitInfo);

    /**
     *
     * @param id
     * @return
     */
    Optional<MarketingCommissionConfigSplitInfo> find(final long id);

    /**
     *
     * @return
     */
    List<MarketingCommissionSplitInfo> getAll();

}
