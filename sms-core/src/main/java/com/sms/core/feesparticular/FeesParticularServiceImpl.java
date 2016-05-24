package com.sms.core.feesparticular;

import com.sms.core.BaseServiceImpl;
import com.sms.core.repositery.FeesParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("feesParticularServiceImpl")
@Transactional
public class FeesParticularServiceImpl extends BaseServiceImpl<FeesParticular> {

    @Autowired
    public FeesParticularServiceImpl(final FeesParticularRepository feesParticularRepository) {
        super(feesParticularRepository);
    }

    @Override
    protected FeesParticular buildToPersistObject(Long id, FeesParticular schemeFeesInfo) {
        return FeesParticular.toBuilder(schemeFeesInfo).build();
    }
}
