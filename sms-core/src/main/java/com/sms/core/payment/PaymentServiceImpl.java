package com.sms.core.payment;

import com.sms.core.common.FList;
import com.sms.core.repositery.FeesParticularRepository;
import com.sms.core.repositery.PaymentRepository;
import com.sms.core.repositery.StudentRepository;
import com.sms.core.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 25/06/16.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private FeesParticularRepository feesParticularRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,StudentRepository studentRepository, FeesParticularRepository feesParticularRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.feesParticularRepository = feesParticularRepository;

    }

    @Override
    public PaymentDetail makePayment(PaymentInfo paymentRequest) {
        Student student = studentRepository.findByCode(paymentRequest.getStudentCode());
        Payment payment = Payment.builder(Payment.build(paymentRequest))
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
    public PaymentDetail getPaymentDetail(String studentCode) {
        return PaymentDetailCalculator.calculatePaymentDetail(studentRepository.findByCode(studentCode));
    }

    @Override
    public List<PaymentSearchInfo> search(PaymentSearchCriteria paymentSearchCriteria) {
        return PaymentSearchService
                .search(paymentSearchCriteria)
                .with(paymentRepository);
    }
}
