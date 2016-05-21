package com.sms.core.scheme;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("schemeServiceImpl")
@Transactional
public class SchemeServiceImpl extends BaseServiceConvertorImpl<SchemeInfo, Scheme> {

    @Autowired
    public SchemeServiceImpl(SchemeRepository schemeRepository) {
        super(schemeRepository,
                (schemeInfo) -> Scheme.toBuilder(schemeInfo).build(),
                (scheme) -> SchemeInfo.toBuilder(scheme).build());
    }

    @Override
    protected Scheme buildToPersistObject(Long id, SchemeInfo schemeInfo) {
        return Scheme.toBuilder(schemeInfo).build();
    }
}
