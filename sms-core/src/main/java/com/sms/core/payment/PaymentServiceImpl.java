package com.sms.core.payment;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.repositery.FeesParticularRepository;
import com.sms.core.repositery.StudentRepository;
import com.sms.core.student.Student;
import com.sms.core.student.StudentFeesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * Created by Ganesan on 25/06/16.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private StudentRepository studentRepository;
    private FeesParticularRepository feesParticularRepository;

    @Autowired
    public PaymentServiceImpl(StudentRepository studentRepository, FeesParticularRepository feesParticularRepository) {
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
        student.getPayments().add(payment);
        studentRepository.saveAndFlush(student);
        return PaymentDetailCalculator.getPaymentDetail(student);
    }


    @Override
    public PaymentDetail getPaymentDetail(String studentCode) {
        return PaymentDetailCalculator.getPaymentDetail(studentRepository.findByCode(studentCode));
    }
}
