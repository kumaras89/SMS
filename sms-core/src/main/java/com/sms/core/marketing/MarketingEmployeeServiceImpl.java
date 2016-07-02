package com.sms.core.marketing;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("marketingEmployeeService")
@Transactional
public class MarketingEmployeeServiceImpl extends BaseServiceConvertorImpl<MarketingEmployeeInfo, MarketingEmployee> {

    private UserRepository userRepository;

    @Autowired
    public MarketingEmployeeServiceImpl(final MarketingEmployeeRepository marketingEmployeeRepository, final UserRepository userRepository) {
        super(marketingEmployeeRepository,
                (marketingEmployeeInfo) ->
                        MarketingEmployee.toBuilder(marketingEmployeeInfo)
                                .on(MarketingEmployee::getLinkedUser).set(userRepository.findByNameIgnoreCase(marketingEmployeeInfo.getLinkedUser()))
                                .build(),
                (marketingEmployee) -> MarketingEmployeeInfo.toBuilder(marketingEmployee).build());
        this.userRepository = userRepository;
    }

    @Override
    protected MarketingEmployee buildToPersistObject(Long id, MarketingEmployeeInfo marketingEmployeeInfo) {

        return MarketingEmployee.toBuilder(marketingEmployeeInfo)
                .with(MarketingEmployee::getLinkedUser, userRepository.findByNameIgnoreCase(marketingEmployeeInfo.getLinkedUser()))
                .with(MarketingEmployee::getId, id)
                .build();
    }
}