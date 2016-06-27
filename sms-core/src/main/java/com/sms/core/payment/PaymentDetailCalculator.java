package com.sms.core.payment;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.feesparticular.Fees;
import com.sms.core.feesparticular.FeesDetail;
import com.sms.core.feesparticular.FeesInfo;
import com.sms.core.student.Student;
import com.sms.core.student.StudentFees;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class PaymentDetailCalculator {

    public static FeesDetail actualPaymentDetail(Set<StudentFees> studentFees) {
        return feesToFeesDetail(studentFees);
    }

    public static FeesDetail paidDetail(Set<Payment> studentPayments) {
        return Do.of(FList.of(studentPayments)
                .map(PaymentDetailCalculator::paymentToFeesDetail).get())
                .then(PaymentDetailCalculator::add).get();
    }

    public static FeesDetail minus(FeesDetail first, FeesDetail second) {
        return Do.of(FList.of(Arrays.asList(first, second))
                .map(PaymentDetailCalculator::feesDetailToMap)
                .get())
                .then(maps -> mapsToMap(maps, BigDecimal::subtract))
                .then(PaymentDetailCalculator::mapToFeesDetail)
                .get();
    }

    public static PaymentDetail getPaymentDetail(Student student) {
        return Do.of(PaymentDetail.builder()
                .on(PaymentDetail::getActualPaymentDetail).set(actualPaymentDetail(student.getStudentFees()))
                .on(PaymentDetail::getPaidDetail).set(paidDetail(student.getPayments()))
                .on(PaymentDetail::getPaymentHistory).set(FList.of(student.getPayments()).map(PaymentInfo::build).get())
                .build())
                .then(pd -> PaymentDetail
                        .builder(pd)
                        .on(PaymentDetail::getRemainingPaymentDetail).set(minus(pd.getActualPaymentDetail(), pd.getPaidDetail()))
                        .build())
                .get();
    }

    public static Map<String, BigDecimal> mapsToMap(List<Map<String, BigDecimal>> maps, BinaryOperator<BigDecimal> merger) {
        return maps.stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        merger
                ));
    }


    public static Map<String, BigDecimal> feesDetailToMap(FeesDetail feesDetail) {
        return feesDetail.getDetailedFees().stream().collect(Collectors.toMap(FeesInfo::getFeesParticularCode, FeesInfo::getAmount));
    }

    public static Map<String, BigDecimal> feesDetailsToMap(List<FeesDetail> feesDetails) {
        return Do.of(feesDetails)
                .then(fd -> FList.of(fd)
                        .map(PaymentDetailCalculator::feesDetailToMap)
                        .get())
                .then(maps -> mapsToMap(maps, BigDecimal::add))
                .get();
    }

    private static FeesDetail mapToFeesDetail(Map<String, BigDecimal> map) {
        return Do.of(map)
                .then(merged -> merged.entrySet()
                        .stream()
                        .map(e -> new FeesInfo(e.getKey(), e.getValue()))
                        .collect(Collectors.toList()))
                .then(PaymentDetailCalculator::asFeesDetail)
                .get();
    }


    public static FeesDetail add(List<FeesDetail> feesDetails) {
        return Do.of(feesDetails)
                .then(PaymentDetailCalculator::feesDetailsToMap)
                .then(PaymentDetailCalculator::mapToFeesDetail)
                .get();
    }


    public static FeesDetail feesToFeesDetail(Collection<? extends Fees> fees) {
        return Do.of(fees)
                .then(PaymentDetailCalculator::convertAsFeesInfo)
                .then(PaymentDetailCalculator::asFeesDetail).get();
    }


    public static FeesDetail paymentToFeesDetail(Payment payment) {
        return Do.of(payment)
                .then(Payment::getPaymentFees)
                .then(PaymentDetailCalculator::feesToFeesDetail)
                .get();
    }

    public static FeesDetail asFeesDetail(List<FeesInfo> feesInfos) {
        return new FeesDetail(feesInfos.stream().map(FeesInfo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add), feesInfos);
    }

    public static List<FeesInfo> convertAsFeesInfo(Collection<? extends Fees> fees) {
        return FList.of(fees).map(FeesInfo::build).get();
    }
}
