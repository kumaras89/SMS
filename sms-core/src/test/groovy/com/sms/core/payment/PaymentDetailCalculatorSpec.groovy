package com.sms.core.payment

import com.sms.core.common.Builder
import com.sms.core.feesparticular.Fees
import com.sms.core.feesparticular.FeesParticular
import com.sms.core.student.Student
import com.sms.core.student.StudentFees
import spock.lang.Specification

/**
 * Created by Ganesan on 27/06/16.
 */
class PaymentDetailCalculatorSpec extends Specification {

    def getFees = { Class<Fees> feesClass, code, amount  ->
        return Builder.of(feesClass)
                .on({f -> f.getAmount() }).set(amount)
                .on({f -> f.getFeesParticular()}).set(FeesParticular.builder().on({ fp -> fp.getCode()}).set(code).build())
                .build();
    }

    def getStudFees = { code, amount -> getFees(StudentFees.class, code, amount ) }

    def getPaymentFees = { code, amount -> getFees(PaymentFees.class, code, amount ) }

    //how to reuse the assert?
    def void assertFeesDetail(feesDet, tot, amount1, amount2) {
        with(feesDet){
            assert amount == tot
            assert detailedFees.size() == 2
            with(detailedFees.get(0)){
                assert feesParticularCode == "one"
                assert amount == amount1
            }
            with(detailedFees.get(1)){
                assert feesParticularCode == "two"
                assert amount == amount2
            }
        }
    }

    def getPayment = { amount1, amount2 ->
        return Payment.builder()
                .on({p -> p.getPaymentFees() })
                .set(new LinkedHashSet<>(Arrays.asList(getPaymentFees("one", amount1), getPaymentFees("two", amount2))))
                .on({p -> p.getStudent() }).set(Student.builder().on({s -> s.getCode()}).set("Set").build())
                .build();

    }

    def "feesToFeesDetail with happy path" () {
        when:
        def feesList = Arrays.asList(getStudFees("one", new BigDecimal("100")), getStudFees("two", new BigDecimal("200")))
        def feesDetail = PaymentDetailCalculator.feesToFeesDetail(feesList)
        then:
        assertFeesDetail(feesDetail, 300, 100, 200)
    }

    def "paymentToFeesDetail" () {
        when:
        def feesSet = new LinkedHashSet<>(Arrays.asList(getPaymentFees("one", new BigDecimal("100")), getPaymentFees("two", new BigDecimal("200"))))
        def feesDetail = PaymentDetailCalculator.paymentToFeesDetail(Payment.builder().on({p -> p.getPaymentFees()}).set(feesSet).build())
        then:
        assertFeesDetail(feesDetail, 300, 100, 200)
    }

    def "feesDetailToMap"() {
        when:
        def feesList = Arrays.asList(getStudFees("one", new BigDecimal("100")), getStudFees("two", new BigDecimal("200")))
        def feesDetail = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def map = PaymentDetailCalculator.feesDetailToMap(feesDetail)
        then:
        map.size() == 2
        map.get("one") == new BigDecimal("100")
        map.get("two") == new BigDecimal("200")
    }

    def "add with two fees Detail"() {
        when:
        def feesList = Arrays.asList(getStudFees("one", new BigDecimal("100")), getStudFees("two", new BigDecimal("200")))
        def feesDetail1 = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def feesList2 = Arrays.asList(getStudFees("one", new BigDecimal("50")), getStudFees("two", new BigDecimal("100")))
        def feesDetail2 = PaymentDetailCalculator.feesToFeesDetail(feesList2)
        def added = PaymentDetailCalculator.add(Arrays.asList(feesDetail1, feesDetail2))
        then:
        assertFeesDetail(added, 450, 150, 300)
    }

    def "minus with two fees Detail"() {
        when:
        def feesList = Arrays.asList(getStudFees("one", new BigDecimal("100")), getStudFees("two", new BigDecimal("200")))
        def feesDetail1 = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def feesList2 = Arrays.asList(getStudFees("one", new BigDecimal("50")), getStudFees("two", new BigDecimal("100")))
        def feesDetail2 = PaymentDetailCalculator.feesToFeesDetail(feesList2)
        def subtracted = PaymentDetailCalculator.minus(feesDetail1, feesDetail2)
        then:
        assertFeesDetail(subtracted, 150, 50, 100)
    }

    def "getPaymentDetail with over all complex student"() {
        when:
          def student =  Student.builder()
                  .on({s -> s.getStudentFees()})
                  .set(new LinkedHashSet<>(Arrays.asList(getStudFees("one", new BigDecimal("500")), getStudFees("two", new BigDecimal("1000")))))
                  .on({s -> s.getPayments()})
                  .set(new LinkedHashSet<>(Arrays.asList(getPayment(new BigDecimal("100"), new BigDecimal("0")), getPayment(new BigDecimal("100"), new BigDecimal("550")))))
                  .build()
           def paymentDetail = PaymentDetailCalculator.calculatePaymentDetail(student);
        then:
        assertFeesDetail(paymentDetail.actualPaymentDetail, 1500, 500, 1000)
        assertFeesDetail(paymentDetail.paidDetail, 750, 200, 550)
        assertFeesDetail(paymentDetail.remainingPaymentDetail, 750, 300, 450)
    }
}
