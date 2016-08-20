package com.sms.core.expense;

import com.sms.core.common.Builder;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 8/16/2016.
 */
public class ExpenseInfo {

    private long id;

    @NotNull(message = "Branch is Empty")
    private String branchCode;

    @NotNull(message = "User name is Empty")
    private String userName;

    @NotNull(message = "Total amount is Empty")
    private BigDecimal totalAmount;

    @NotNull(message = "Expenses date is Empty")
    private Date expenseDate;

    @Valid
    @NotNull(message = "Expense Details is Empty")
    private Set<ExpenseDetails> expenseDetails;

    private Date creationDate;

    private Date modificationDate;

    public ExpenseInfo() {

    }

    public static Builder<ExpenseInfo> builder() {
        return Builder.of(ExpenseInfo.class);
    }

    public static Builder<ExpenseInfo> toBuilder(final Expense expense) {
        return builder()
                .on(ExpenseInfo::getId).set(expense.getId())
                .on(ExpenseInfo::getCreationDate).set(expense.getCreationDate())
                .on(ExpenseInfo::getModificationDate).set(expense.getModificationDate())
                .on(ExpenseInfo::getExpenseDetails).set(expense.getExpenseDetails())
                .on(ExpenseInfo::getBranchCode).set(expense.getBranch().getCode())
                .on(ExpenseInfo::getUserName).set(expense.getUser().getName())
                .on(ExpenseInfo::getTotalAmount).set(expense.getTotalAmount())
                .on(ExpenseInfo::getExpenseDate).set(expense.getExpensesDate())
                ;
    }


    public long getId() {
        return id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getUserName() {
        return userName;
    }

    public Set<ExpenseDetails> getExpenseDetails() {
        return expenseDetails;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }
}
