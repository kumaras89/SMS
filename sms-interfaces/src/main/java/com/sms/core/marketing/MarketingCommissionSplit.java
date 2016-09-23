package com.sms.core.marketing;

import com.sms.core.common.Builder;
import com.sms.core.student.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 18:03.
 *  
 */
@Entity
@Table(name = "SMS_TR_MARKETING_COMMISSION_SPLIT")
@SequenceGenerator(sequenceName = "SMS_SQ_MCS", name = "SMS_SQ_MCS", allocationSize = 1)
public class MarketingCommissionSplit implements Serializable {
    private static final long serialVersionUID = -1277948482773314043L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_MCS")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MCS_STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "MCS_MARKETING_EMPLOYEE_ID")
    private MarketingEmployee referencePerson;

    @Column(name = "MCS_AMOUNT")
    private BigDecimal amount;


    public static Builder<MarketingCommissionSplit> builder() {
        return Builder.of(MarketingCommissionSplit.class);
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public MarketingEmployee getReferencePerson() {
        return referencePerson;
    }

    public BigDecimal getAmount() {
        return amount;
    }


}
