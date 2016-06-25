package com.sms.core.repositery;

import com.sms.core.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ganesan on 25/06/16.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long>  {
}
