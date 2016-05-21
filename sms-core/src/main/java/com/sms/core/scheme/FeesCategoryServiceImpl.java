package com.sms.core.scheme;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.FeesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("feesCategoryServiceImpl")
@Transactional
public class FeesCategoryServiceImpl extends BaseServiceConvertorImpl<FeesCategoryInfo, FeesCategory> {

    @Autowired
    public FeesCategoryServiceImpl(FeesCategoryRepository feesCategoryRepository) {

        super(feesCategoryRepository,
                (feesCategoryInfo) -> FeesCategory.toBuilder(feesCategoryInfo).build(),
                (feesCategory) -> FeesCategoryInfo.toBuilder(feesCategory).build());
    }

    @Override
    protected FeesCategory buildToPersistObject(Long id, FeesCategoryInfo feesCategoryInfo) {
        return FeesCategory.toBuilder(feesCategoryInfo).build();
    }
}
