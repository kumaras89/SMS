package com.sms.core.marketing;

import com.sms.core.common.Designation;
import com.sms.core.repositery.MarketingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:41.
 *  
 */
@Component
public class MarketingEmployeeSuperiorService implements IMarketingEmployeeSuperiorService {

    @Autowired
    private MarketingEmployeeRepository repository;

    @Override
    public List<MarketingEmployee> getAllMarketingEmployee(final Designation designation) {

        return Optional.of(designation)
            .map(Designation::getSuperior)
            .filter(s -> !s.isEmpty())
            .map
                (Designation::valueOf)
            .map(repository::findByDesignation)
            .orElse(Collections.emptyList());
    }
}
