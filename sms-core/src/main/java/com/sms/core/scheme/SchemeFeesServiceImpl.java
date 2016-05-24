package com.sms.core.scheme;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.SchemeFeesRepository;
import com.sms.core.repositery.FeesParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("schemeFeesServiceImpl")
@Transactional
public class SchemeFeesServiceImpl extends BaseServiceConvertorImpl<SchemeFeesInfo, SchemeFees> {

    private FeesParticularRepository feesParticularRepository;

    @Autowired
    public SchemeFeesServiceImpl(final SchemeFeesRepository schemeFeesRepository,
                                 final FeesParticularRepository feesParticularRepository) {

        super(schemeFeesRepository,
                (feesCategoryInfo) -> SchemeFees.toBuilder(feesCategoryInfo)
                        .withFeesParticular(feesParticularRepository.findByCodeIgnoreCase(feesCategoryInfo.getFeesParticularCode()))
                        .build(),
                (feesCategory) -> SchemeFeesInfo.toBuilder(feesCategory).build());
        this.feesParticularRepository = feesParticularRepository;
    }

    @Override
    protected SchemeFees buildToPersistObject(Long id, SchemeFeesInfo schemeFeesInfo) {
        return SchemeFees.toBuilder(schemeFeesInfo)
                .withId(id)
                .withFeesParticular(feesParticularRepository.findByCodeIgnoreCase(schemeFeesInfo.getFeesParticularCode()))
                .build();
    }
}
