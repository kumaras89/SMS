package com.sms.core.marketing;

import com.sms.core.common.Designation;
import com.sms.core.repositery.MarketingCommissionSplitConfigRepository;
import com.sms.core.repositery.MarketingCommissionSplitRepository;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:41.
 *  
 */
@Component
public class MarketingEmployeeSuperiorService implements IMarketingEmployeeSuperiorService {

    @Autowired
    private MarketingEmployeeRepository repository;

    @Autowired
    private MarketingCommissionSplitConfigRepository configRepository;

    @Autowired
    private MarketingCommissionSplitRepository splitRepository;

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

    @Override
    public void generateMarketingCommissionSplit(final Student student) {
        final MarketingEmployee marketingEmployee = student.getMarketingEmployee();

        final Designation designation = marketingEmployee.getDesignation();

        final List<MarketingCommissionSplitConfig> bySubOrdinate = configRepository.findBySubOrdinate(designation);

        final Map<Designation, BigDecimal> splitConfigMap =
            bySubOrdinate
                .stream()
                .collect(Collectors.toMap(
                    MarketingCommissionSplitConfig::getSuperior,
                    MarketingCommissionSplitConfig::getAmount));

        Optional<MarketingEmployee> employeeOptional = Optional.of(marketingEmployee);

        do {
            final MarketingEmployee employee = employeeOptional.get();
            final MarketingCommissionSplit marketingCommissionSplit =
                MarketingCommissionSplit
                    .builder()
                    .on(MarketingCommissionSplit::getStudent).set(student)
                    .on(MarketingCommissionSplit::getReferencePerson).set(employee)
                    .on(MarketingCommissionSplit::getAmount).set(splitConfigMap.get(employee.getDesignation()))
                    .build();
            splitRepository.save(marketingCommissionSplit);
            employeeOptional = Optional.ofNullable(employee.getSuperior());

        } while (employeeOptional.isPresent());

    }

}
