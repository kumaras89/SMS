package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.common.Reader;
import com.sms.core.feesparticular.FeesInfo;
import com.sms.core.student.Student;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Ram on 18-06-2016.
 */
@Entity
@Table(name = "SMS_TR_PAYMENT")
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "SP_AMOUNT")
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SP_STUDENT_ID")
    private Student student;

    @Column(name = "SP_DD_NUMBER")
    private String ddNumber;

    @Column(name = "SP_PAID_DATE")
    private Date paidDate;

    @Column(name = "SP_BANK_NAME")
    private String bankName;

    @Column(name = "SP_BANK_BRANCH_NAME")
    private String bankBranchName;

    @Column(name = "SP_ADMITTED_REJECTED_BY")
    private String admittedOrRejectedBy;

    @Column(name = "SP_APPROVED_BY")
    private String approvedBy;

    @Column(name = "SP_ADMITTED_BY")
    private String admittedBy;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SP_PAYMENT_ID")
    private Set<PaymentFees> paymentFees;


    public Set<PaymentFees> getPaymentFees() {
        return paymentFees;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDdNumber() {
        return ddNumber;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public String getAdmittedOrRejectedBy() {
        return admittedOrRejectedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public String getAdmittedBy() {
        return admittedBy;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public static Builder<Payment> builder() {
        return Builder.of(Payment.class);
    }

    public static Builder<Payment> builder(Payment payment) {
        return Builder.of(Payment.class, payment);
    }

    public static Payment build(PaymentInfo payment) {
        return builder()
                .on(Payment::getAmount).set(payment.getAmount())
                .on(Payment::getDdNumber).set(payment.getDdNumber())
                .on(Payment::getBankName).set(payment.getBankName())
                .on(Payment::getPaidDate).set(payment.getPaidDate())
                .on(Payment::getBankBranchName).set(payment.getBankBranchName())
                .on(Payment::getAdmittedOrRejectedBy).set(payment.getAdmittedOrRejectedBy())
                .on(Payment::getApprovedBy).set(payment.getApprovedBy())
                .on(Payment::getAdmittedBy).set(payment.getAdmittedBy())
                .build();
    }


}
