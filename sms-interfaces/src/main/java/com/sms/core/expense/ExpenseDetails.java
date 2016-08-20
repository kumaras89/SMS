package com.sms.core.expense;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by sathish on 8/16/2016.
 */
@Entity
@Table(name = "SMS_TR_EXPENSE_DETAILS")
@SequenceGenerator(name = "SMS_SQ_EXD",sequenceName = "SMS_SQ_EXD", allocationSize = 1)
public class ExpenseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_EXD")
    private long id;

    @Column(name = "EXP_REASON")
    @NotNull(message = "Reason is Empty")
    private String reason;

    @Column(name = "EXP_AMOUNT")
    @NotNull(message = "Amount is Empty")
    private BigDecimal amount;

    public long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
