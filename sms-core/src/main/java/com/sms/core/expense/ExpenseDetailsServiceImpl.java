package com.sms.core.expense;

import com.sms.core.SmsException;
import com.sms.core.common.OptionalExt;
import com.sms.core.repositery.ExpenseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sathish on 10/4/2016.
 */
@Transactional
@Service
public class ExpenseDetailsServiceImpl implements ExpenseDetailsService {

    @Autowired
    ExpenseDetailsRepository expenseDetailsRepository;

    /**
     * Deleting Expense Details
     *
     * @param id
     */
    @Override
    public void deleteExpenseDetails(final long id) {
        OptionalExt.of(expenseDetailsRepository.findById(id))
                .ifPresentOrElse(
                        expenseDetailsRepository::delete,
                        () -> new SmsException(
                                "ExpenseDetails Delete Error",
                                "What are you trying to delete its not available"));
    }
}
