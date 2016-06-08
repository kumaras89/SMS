package com.sms.core.marketing;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.MarketingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("marketingEmployeeService")
@Transactional
public class MarketingEmployeeServiceImpl extends BaseServiceConvertorImpl<MarketingEmployeeInfo, MarketingEmployee> {

    @Autowired
    public MarketingEmployeeServiceImpl(final MarketingEmployeeRepository marketingEmployeeRepository) {

        super(marketingEmployeeRepository,
                (marketingEmployeeInfo) ->
                        MarketingEmployee.toBuilder(marketingEmployeeInfo).build(),
                (marketingEmployee) -> MarketingEmployeeInfo.toBuilder(marketingEmployee).build());
    }

    @Override
    protected MarketingEmployee buildToPersistObject(Long id, MarketingEmployeeInfo marketingEmployeeInfo) {

        return MarketingEmployee.toBuilder(marketingEmployeeInfo)
                .with(MarketingEmployee::getId, id)
                .build();
    }
}