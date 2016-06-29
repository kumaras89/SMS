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

    def getFeesObj = { Class<Fees> feesClass, code, amount  ->
        return Builder.of(feesClass)
                .on({f -> f.getAmount() }).set(new BigDecimal(amount))
                .on({f -> f.getFeesParticular()}).set(FeesParticular.builder().on({ fp -> fp.getCode()}).set(code).build())
                .build();
    }

    def getFees(Class<Fees> feesClass, amount1, amount2) {
        return new LinkedHashSet<>(Arrays.asList(getFeesObj(feesClass, "one", amount1), getFeesObj(feesClass, "two", amount2)))
    }

    def void assertFeesPurticular(feesPurticular, code, amount1) {
        with(feesPurticular){
            assert feesParticularCode == code
            assert amount == amount1
        }
    }
    def void assertFeesDetail(feesDet, tot, amount1, amount2) {
        with(feesDet){
            assert amount == tot
            assert detailedFees.size() == 2
            assertFeesPurticular(detailedFees.get(0), "one", amount1)
            assertFeesPurticular(detailedFees.get(1), "two", amount2)
        }
    }

    def getPayment = { amount1, amount2 ->
        return Payment.builder()
                .on({p -> p.getPaymentFees() })
                .set(getFees(PaymentFees.class, amount1, amount2))
                .on({p -> p.getStudent() }).set(Student.builder().on({s -> s.getCode()}).set("Set").build())
                .build();

    }

    def "feesToFeesDetail" () {
        when:
        def feesList = getFees(PaymentFees.class, "100", "200")
        def feesDetail = PaymentDetailCalculator.feesToFeesDetail(feesList)
        then:
        assertFeesDetail(feesDetail, 300, 100, 200)
    }

    def "paymentToFeesDetail" () {
        when:
        def feesSet = getFees(PaymentFees.class, "100", "200")
        def feesDetail = PaymentDetailCalculator.paymentToFeesDetail(Payment.builder().on({p -> p.getPaymentFees()}).set(feesSet).build())
        then:
        assertFeesDetail(feesDetail, 300, 100, 200)
    }

    def "feesDetailToMap"() {
        when:
        def feesList = getFees(PaymentFees.class, "100", "200")
        def feesDetail = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def map = PaymentDetailCalculator.feesDetailToMap(feesDetail)
        then:
        map.size() == 2
        map.get("one") == new BigDecimal("100")
        map.get("two") == new BigDecimal("200")
    }

    def "add with two fees Detail"() {
        when:
        def feesList = getFees(PaymentFees.class, "100", "200")
        def feesDetail1 = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def feesList2 = getFees(PaymentFees.class, "50", "100")
        def feesDetail2 = PaymentDetailCalculator.feesToFeesDetail(feesList2)
        def added = PaymentDetailCalculator.add(Arrays.asList(feesDetail1, feesDetail2))
        then:
        assertFeesDetail(added, 450, 150, 300)
    }

    def "minus with two fees Detail"() {
        when:
        def feesList = getFees(PaymentFees.class, "100", "200")
        def feesDetail1 = PaymentDetailCalculator.feesToFeesDetail(feesList)
        def feesList2 = getFees(PaymentFees.class, "50", "100")
        def feesDetail2 = PaymentDetailCalculator.feesToFeesDetail(feesList2)
        def subtracted = PaymentDetailCalculator.minus(feesDetail1, feesDetail2)
        then:
        assertFeesDetail(subtracted, 150, 50, 100)
    }

    def "getPaymentDetail with over all complex student"() {
        when:
          def student =  Student.builder()
                  .on({s -> s.getStudentFees()})
                  .set(getFees(StudentFees.class, "500", "1000"))
                  .on({s -> s.getPayments()})
                  .set(new LinkedHashSet<>(Arrays.asList(getPayment("100", "0"), getPayment("100","550"))))
                  .build()
           def paymentDetail = PaymentDetailCalculator.calculatePaymentDetail(student);
        then:
        assertFeesDetail(paymentDetail.actualPaymentDetail, 1500, 500, 1000)
        assertFeesDetail(paymentDetail.paidDetail, 750, 200, 550)
        assertFeesDetail(paymentDetail.remainingPaymentDetail, 750, 300, 450)
    }
}
