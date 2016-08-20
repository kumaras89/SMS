package com.sms.core.expense;

import com.sms.core.common.*;
import com.sms.core.repositery.ExpenseRepository;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/16/2016.
 */
public class ExpenseSearchService
{
    public static Specification<Expense> expenseSearchSpec(final ExpenseSearchCriteria criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
                .map(ExpenseSearchCriteria::getUserName, name -> builder.like(root.join("user",  JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(ExpenseSearchCriteria::getBranchName, name -> builder.like(root.join("branch",  JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(ExpenseSearchCriteria::getDurationFrom, durationFrom -> builder.greaterThanOrEqualTo(root.<Date>get("creationDate"),durationFrom))
                .map(ExpenseSearchCriteria::getDurationTo, durationTo -> builder.lessThanOrEqualTo(root.<Date>get("creationDate"),durationTo))
                .getArray());
    }

    public static Reader<ExpenseRepository, List<ExpenseInfo>> search(ExpenseSearchCriteria expenseSearchCriteria) {
        return Reader.of(expenseRepository ->
                Do.of(expenseSearchCriteria)
                        .then(criteria -> expenseSearchSpec(expenseSearchCriteria))
                        .then(spec -> expenseRepository.findAll(spec))
                        .then(expenses -> FList.of(expenses).map(eInfo -> ExpenseInfo.toBuilder(eInfo).build()).get())
                        .get());
    }
}
