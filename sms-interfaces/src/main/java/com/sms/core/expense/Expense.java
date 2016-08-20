package com.sms.core.expense;

import com.sms.core.admin.User;
import com.sms.core.branch.Branch;
import com.sms.core.common.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 8/16/2016.
 */


@Entity
@Table(name = "SMS_TR_EXPENSE")
@SequenceGenerator(name = "SMS_SQ_EXP", sequenceName = "SMS_SQ_EXP", allocationSize = 1)
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_EXP")
    private long id;

    @ManyToOne
    @JoinColumn(name = "SMS_BRANCH_CODE")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "SMS_USER_NAME")
    private User user;

    @Column(name = "SMS_CREATION_DATE")
    private Date creationDate;

    @Column(name = "SMS_MODIFICATION_DATE")
    private Date modificationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EXP_EXPENSE_ID")
    private Set<ExpenseDetails> expenseDetails;

    @Column(name = "SMS_EXP_DATE")
    private Date expensesDate;

    @Column(name = "SMS_EXP_TAMOUNT")
    private BigDecimal totalAmount;

    public static Builder<Expense> builder() {
        return Builder.of(Expense.class);
    }

    public static Builder<Expense> toBuilder(final ExpenseInfo expenseInfo) {
        return builder()
                .on(Expense::getId).set(expenseInfo.getId())
                .on(Expense::getCreationDate).set(new Date())
                .on(Expense::getModificationDate).set(new Date())
                .on(Expense::getExpenseDetails).set(expenseInfo.getExpenseDetails())
                .on(Expense::getTotalAmount).set(expenseInfo.getTotalAmount())
                .on(Expense::getExpensesDate).set(expenseInfo.getExpenseDate());
    }


    public long getId() {
        return id;
    }

    public Branch getBranch() {
        return branch;
    }

    public User getUser() {
        return user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public Set<ExpenseDetails> getExpenseDetails() {
        return expenseDetails;
    }

    public Date getExpensesDate() {
        return expensesDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
