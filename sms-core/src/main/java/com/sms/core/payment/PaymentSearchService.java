package com.sms.core.payment;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.PredicateBuilder;
import com.sms.core.common.Reader;
import com.sms.core.repositery.PaymentRepository;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Assaycr-04 on 8/11/2016.
 */
public class PaymentSearchService {

    public static Specification<Payment> paymentRequest(PaymentSearchCriteria criteria){
        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
                .map(PaymentSearchCriteria::getDurationTo, durationTo -> builder.lessThanOrEqualTo(root.<Date>get("paidDate"),durationTo))
                .map(PaymentSearchCriteria::getDurationFrom, durationFrom -> builder.greaterThanOrEqualTo(root.<Date>get("paidDate"),durationFrom))
                .map( PaymentSearchCriteria::getStudentName, name-> builder.like(builder.upper(root.join("student", JoinType.LEFT).get("name")), "%" +name.toUpperCase() +"%"))
                .map( PaymentSearchCriteria::getStudentCode, code-> builder.like(builder.upper(root.join("student", JoinType.LEFT).get("code")), "%" +code.toUpperCase() +"%"))
                .map( PaymentSearchCriteria::getBranchName, branch-> builder.like(builder.upper(root.join("student", JoinType.LEFT).get("branch").get("name")), "%" +branch.toUpperCase() +"%"))
                .map( PaymentSearchCriteria::getBatchName, batch-> builder.like(builder.upper(root.join("student", JoinType.LEFT).get("batch").get("name")), "%" +batch.toUpperCase() +"%"))
                .getArray());
    }

    public static Reader<PaymentRepository, List<PaymentSearchInfo>> search(final PaymentSearchCriteria paymentSearchCriteria) {
        return Reader.of(paymentRepo -> Do.of(paymentSearchCriteria)
                        .then(criteria -> paymentRequest(criteria))
                        .then(spec -> paymentRepo.findAll(spec))
                        .then(pay -> FList.of(pay).map(pInfo -> PaymentSearchInfo.build(pInfo)).get())
                        .get());
    }
}