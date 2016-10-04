package com.sms.core.expense;

import com.sms.core.SmsException;
import com.sms.core.common.CollectorUtils;
import com.sms.core.common.FList;
import com.sms.core.common.OptionalExt;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.ExpenseDetailsRepository;
import com.sms.core.repositery.ExpenseRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/16/2016.
 * <p></p>
 */
@Transactional
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;

    private static ExpenseInfo expenseInfo(final Expense source) {
        return ExpenseInfo.toBuilder(source).build();
    }

    /**
     * @param expenseInfo
     * @param createdDate
     * @return
     */
    public Optional<ExpenseInfo> commonSave(final ExpenseInfo expenseInfo, final Date createdDate) {
        return Optional.of(
                expenseRepository.saveAndFlush(
                        Expense.toBuilder(expenseInfo)
                                .on(Expense::getBranch).set(branchRepository.findByCodeIgnoreCase(expenseInfo.getBranchCode()))
                                .on(Expense::getUser).set(userRepository.findByNameIgnoreCase(expenseInfo.getUserName()))
                                .on(Expense::getCreationDate).set(createdDate)
                                .build()))
                .map(ExpenseServiceImpl::expenseInfo);
    }

    /**
     * @param entityType
     * @return
     */
    @Override
    public Optional<ExpenseInfo> save(final ExpenseInfo entityType) {
        return this.commonSave(entityType, new Date());
    }

    /**
     * @param id
     * @param expenseInfo
     * @return
     */
    @Override
    public Optional<ExpenseInfo> update(final long id, final ExpenseInfo expenseInfo) {
        return Optional.ofNullable(expenseRepository.findById(id))
                .map(expense -> this.commonSave(expenseInfo, expense.getCreationDate()))
                .orElseThrow(
                        () -> new SmsException("Expense updation Error", "What are you trying to update its not available"));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<ExpenseInfo> findById(final long id) {
        return Optional.of(this.expenseRepository.findById(id)).map(ExpenseServiceImpl::expenseInfo);
    }

    /**
     * @return
     */
    @Override
    public List<ExpenseInfo> findAll() {
        return FList.of(this.expenseRepository.findAll()).map(ExpenseServiceImpl::expenseInfo).get();
    }

    /**
     * @param expenseSearchCriteria
     * @return
     */
    @Override
    public List<ExpenseInfo> search(final ExpenseSearchCriteria expenseSearchCriteria) {
        return ExpenseSearchService.search(expenseSearchCriteria).with(expenseRepository);
    }

    /**
     * Deleting Expense
     *
     * @param id
     */
    @Override
    public void deleteExpense(final long id) {
        OptionalExt.of(expenseRepository.findById(id))
                .ifPresentOrElse(
                        expense -> expenseRepository.delete(expense),
                        () -> new SmsException("Expense Delete Error", "What are you trying to delete its not available"));
    }


    /**
     * @param expenseSearchCriteria
     * @return
     */
    @Override
    public BigDecimal totalExpense(final ExpenseSearchCriteria expenseSearchCriteria) {
        return ExpenseSearchService
                .search(expenseSearchCriteria)
                .with(expenseRepository)
                .stream()
                .collect(CollectorUtils.grouping(() -> BigDecimal.ZERO, ExpenseInfo::getTotalAmount, BigDecimal::add));
    }
}
