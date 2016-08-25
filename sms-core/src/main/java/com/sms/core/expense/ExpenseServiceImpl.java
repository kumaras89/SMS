package com.sms.core.expense;

import com.sms.core.SmsException;
import com.sms.core.common.FList;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.ExpenseDetailsRepository;
import com.sms.core.repositery.ExpenseRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/16/2016.
 */

@Transactional
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final ExpenseDetailsRepository expenseDetailsRepository;

    @Autowired
    public ExpenseServiceImpl
            (final ExpenseRepository expenseRepository,
             final UserRepository userRepository,
             final BranchRepository branchRepository,
             final ExpenseDetailsRepository expenseDetailsRepository) {
        this.expenseRepository = expenseRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.expenseDetailsRepository = expenseDetailsRepository;
    }

    private static ExpenseInfo expenseInfo(final Expense source) {
        return ExpenseInfo.toBuilder(source).build();
    }

    public Optional<ExpenseInfo> commonSave(final ExpenseInfo expenseInfo, Date createdDate) {
        return Optional.of(expenseRepository.saveAndFlush(Expense.toBuilder(expenseInfo)
                .on(Expense::getBranch).set(branchRepository.findByCodeIgnoreCase(expenseInfo.getBranchCode()))
                .on(Expense::getUser).set(userRepository.findByNameIgnoreCase(expenseInfo.getUserName()))
                .on(Expense::getCreationDate).set(createdDate)
                .build())).map(ExpenseServiceImpl::expenseInfo);
    }

    @Override
    public Optional<ExpenseInfo> save(final ExpenseInfo entityType) {
        return this.commonSave(entityType, new Date());
    }

    @Override
    public Optional<ExpenseInfo> update(final long id, final ExpenseInfo expenseInfo) {
        Expense alreadyExist = expenseRepository.findById(id);
        if (alreadyExist != null) {
            return this.commonSave(expenseInfo, alreadyExist.getCreationDate());
        } else {
            throw new SmsException("Expense updation Error", "What are you trying to update its not available");
        }
    }

    @Override
    public Optional<ExpenseInfo> findById(final long id) {
        return Optional.of(this.expenseRepository.findById(id))
                .map(ExpenseServiceImpl::expenseInfo);
    }

    @Override
    public List<ExpenseInfo> findAll() {
        return FList.of(this.expenseRepository.findAll()).map(ExpenseServiceImpl::expenseInfo).get();
    }

    @Override
    public List<ExpenseInfo> search(final ExpenseSearchCriteria expenseSearchCriteria) {
        return ExpenseSearchService
                .search(expenseSearchCriteria)
                .with(expenseRepository);
    }

    /**
     * Deleting Expense
     * @param id
     */
    @Override
    public void deleteExpense(final long id)
    {
        Expense alreadyExist = expenseRepository.findById(id);
        if (alreadyExist != null) {
            expenseRepository.delete(alreadyExist);
        } else {
            throw new SmsException("Expense Delete Error", "What are you trying to delete its not available");
        }
    }

    /**
     * Deleting Expense Details
     * @param id
     */
    @Override
    public void deleteExpenseDetails(final long id)
    {
        ExpenseDetails alreadyExist = expenseDetailsRepository.findById(id);
        if (alreadyExist != null) {
            expenseDetailsRepository.delete(alreadyExist);
        } else {
            throw new SmsException("ExpenseDetails Delete Error", "What are you trying to delete its not available");
        }
    }
}
