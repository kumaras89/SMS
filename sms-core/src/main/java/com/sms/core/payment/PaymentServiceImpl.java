package com.sms.core.payment;

import com.sms.core.repositery.StudentRepository;
import com.sms.core.student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ganesan on 25/06/16.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private StudentRepository studentRepository;


    @Override
    public PaymentDetail makePayment(PaymentInfo paymentRequest) {
        //query student
        //create new payment object
        //add it to the student
        //save student
        //return payment detail
        return calculate(null);
    }

    private static PaymentDetail calculate(Student student) {
        PaymentDetail paymentDetail = new PaymentDetail();
        //get the actual payment to be made by that student using student.getStudentFees()
        //paidDetail = sum of all pyaments in student.getPayments
        //paiddetail - actualpayment
        //student.paymetns into payment info and add it to paymentHistory
        return paymentDetail;

    }

    @Override
    public PaymentDetail getPaymentDetail(String studentCode) {
        //query the student
        return calculate(null);
    }
}
