package com.sms.core.repositery;

import com.sms.core.expense.ExpenseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Assaycr-02 on 8/25/2016.
 */
public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetails,String> {
    ExpenseDetails findById(final long id);
}