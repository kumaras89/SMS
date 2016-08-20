package com.sms.core.expense;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/16/2016.
 */
public interface ExpenseService {

    /**
     *
     * @param entityType
     * @return
     */

    Optional<ExpenseInfo> save(final ExpenseInfo entityType);

    /**
     *
     * @param id
     * @param expenseInfo
     * @return
     */

    Optional<ExpenseInfo> update(final long id, final ExpenseInfo expenseInfo);

    /**
     *
     * @param id
     * @return
     */
    Optional<ExpenseInfo> findById(final long id);

    /**
     *
     * @return
     */

    List<ExpenseInfo> findAll();

    /**
     *
     * @param expenseSearchCriteria
     * @return
     */

    List<ExpenseInfo> search(final ExpenseSearchCriteria expenseSearchCriteria);
}
