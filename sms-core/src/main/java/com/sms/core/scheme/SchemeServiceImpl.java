package com.sms.core.scheme;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.FeesParticularRepository;
import com.sms.core.repositery.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service("schemeServiceImpl")
@Transactional
public class SchemeServiceImpl extends BaseServiceConvertorImpl<SchemeInfo, Scheme> {

    private FeesParticularRepository feesParticularRepository;

    @Autowired
    public SchemeServiceImpl(SchemeRepository schemeRepository, FeesParticularRepository feesParticularRepository) {
        super(schemeRepository,
                (schemeInfo) -> Scheme.toBuilder(schemeInfo)
                        .with(Scheme::getSchemeFees,schemeInfo.getFeesInfos().stream()
                                .map(scheme -> SchemeFees.toBuilder(scheme)
                                        .on(s -> s.getFeesParticular()).set(feesParticularRepository.findByCodeIgnoreCase(scheme.getFeesParticularCode()))
                                        .build())
                                .collect(Collectors.toSet()))
                .build(),
                (scheme) -> SchemeInfo.toBuilder(scheme).build());
        this.feesParticularRepository = feesParticularRepository;
    }

    @Override
    protected Scheme buildToPersistObject(Long id, SchemeInfo schemeInfo) {

        return Scheme.toBuilder(schemeInfo)
                .with(Scheme::getId,id)
                .with(Scheme::getSchemeFees,schemeInfo.getFeesInfos().stream()
                .map(scheme -> SchemeFees.toBuilder(scheme)
                        .with(Fees::getFeesParticular, feesParticularRepository.findByCodeIgnoreCase(scheme.getFeesParticularCode()))
                        .build())
                .collect(Collectors.toSet()))
                .build();
    }
}
