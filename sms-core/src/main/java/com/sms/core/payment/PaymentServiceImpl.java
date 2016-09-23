package com.sms.core.payment;

import com.sms.core.common.CollectorUtils;
import com.sms.core.common.FList;
import com.sms.core.repositery.FeesParticularRepository;
import com.sms.core.repositery.PaymentRepository;
import com.sms.core.repositery.StudentRepository;
import com.sms.core.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 25/06/16.
 * <p></p>
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FeesParticularRepository feesParticularRepository;

    @Override
    public PaymentDetail makePayment(final PaymentInfo paymentRequest) {
        final Student student = studentRepository.findByCode(paymentRequest.getStudentCode());
        final Payment payment = Payment.builder(Payment.build(paymentRequest))
            .on(Payment::getStudent).set(student)
            .on(Payment::getPaymentFees).set(FList.of(paymentRequest.getFeesInfos())
                .map(feesInfo -> PaymentFees.build(feesInfo).with(feesParticularRepository))
                .get(Collectors.toSet()))
            .build();
        Optional.ofNullable(student.getPayments()).orElse(new HashSet<>()).add(payment);
        studentRepository.saveAndFlush(student);
        return PaymentDetailCalculator.calculatePaymentDetail(student);
    }


    @Override
    public PaymentDetail getPaymentDetail(final String studentCode) {
        return PaymentDetailCalculator.calculatePaymentDetail(studentRepository.findByCode(studentCode));
    }

    @Override
    public List<PaymentSearchInfo> search(final PaymentSearchCriteria paymentSearchCriteria) {
        return PaymentSearchService
            .search(paymentSearchCriteria)
            .with(paymentRepository);
    }

    @Override
    public BigDecimal totalIncome(final PaymentSearchCriteria paymentSearchCriteria) {
        return PaymentSearchService
            .search(paymentSearchCriteria)
            .with(paymentRepository)
            .stream()
            .collect(
                CollectorUtils.grouping(() -> BigDecimal.ZERO,
                    info -> info.getPaymentInfo().getAmount(),
                    BigDecimal::add));
    }
}
