package com.sms.core.identity;

import com.sms.core.common.PredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public class IDCardSpecification {

    public static Specification<IdCard> isLongTermCustomer(Optional<IdCardSearchCriteria> criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(criteria)
                .map(IdCardSearchCriteria::getUploaderId, uploaderId -> builder.like(builder.upper(root.<String>get("uploaderId")), "%" + uploaderId + "%"))
                .map(IdCardSearchCriteria::getStatus, status -> builder.equal(root.<String>get("status"), status))
                .map(IdCardSearchCriteria::getUploaderCategory, uploadCat -> builder.equal(root.<String>get("uploaderCategory"), uploadCat))
                .map(IdCardSearchCriteria::getYear, year -> builder.between(root.<LocalDate>get("createdDate"), LocalDate.of(year, Month.JANUARY, 1), LocalDate.of(year, Month.DECEMBER, 31)))
                .getArray());
    }

}

