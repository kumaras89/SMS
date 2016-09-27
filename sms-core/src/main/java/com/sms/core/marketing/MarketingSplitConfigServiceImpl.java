package com.sms.core.marketing;

import com.sms.core.SmsException;
import com.sms.core.common.FList;
import com.sms.core.repositery.MarketingCommissionSplitConfigRepository;
import com.sms.core.repositery.MarketingCommissionSplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 9/26/2016.
 */
@Service
@Transactional
public class MarketingSplitConfigServiceImpl implements MarketingSplitConfigService {
    @Autowired
    private MarketingCommissionSplitConfigRepository configRepository;

    @Autowired
    private MarketingCommissionSplitRepository commissionSplitRepository;

    private static MarketingCommissionConfigSplitInfo configToInfo(final MarketingCommissionSplitConfig source) {
        return MarketingCommissionConfigSplitInfo.toBuilder(source).build();
    }

    private static MarketingCommissionSplitInfo splitInfo(final MarketingCommissionSplit source) {
        return MarketingCommissionSplitInfo.toBuilder(source).build();
    }

    @Override
    public List<MarketingCommissionConfigSplitInfo> findAll() {
        return FList.of(this.configRepository.findAll()).map(MarketingSplitConfigServiceImpl::configToInfo).get();
    }

    @Override
    public Optional<MarketingCommissionConfigSplitInfo> save(final MarketingCommissionConfigSplitInfo entityType) {

        return Optional.of(configRepository.save(MarketingCommissionSplitConfig.toBuilder(entityType).build())).map(MarketingSplitConfigServiceImpl::configToInfo);
    }

    @Override
    public Optional<MarketingCommissionConfigSplitInfo> update(final long id, final MarketingCommissionConfigSplitInfo editedConfiguration) {
        MarketingCommissionSplitConfig alreadyExists = configRepository.findOne(id);
        if (alreadyExists != null) {
            return Optional.of(configRepository.saveAndFlush(MarketingCommissionSplitConfig.toBuilder(editedConfiguration)
                    .on(MarketingCommissionSplitConfig::getId).set(alreadyExists.getId())
                    .build())).map(MarketingSplitConfigServiceImpl::configToInfo);
        } else {
            throw new SmsException("Updation Error", "Updating Configuration not Exist");
        }
    }

    @Override
    public Optional<MarketingCommissionConfigSplitInfo> find(long id) {
        return Optional.of(configRepository.findOne(id)).map(MarketingSplitConfigServiceImpl::configToInfo);
    }

    @Override
    public List<MarketingCommissionSplitInfo> getAll() {
        return FList.of(commissionSplitRepository.findAll()).map(MarketingSplitConfigServiceImpl::splitInfo).get();
    }

}
