package com.sms.core.repositery;

import com.sms.core.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by sathish on 8/16/2016.
 */
public interface ExpenseRepository extends JpaRepository<Expense,String>,JpaSpecificationExecutor<Expense>
{
    Expense findById(final long id);
}
