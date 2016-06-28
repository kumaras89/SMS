package com.sms.core.identity;

import com.sms.core.common.PredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.Optional;

public class IDCardSpecification {

    public static Specification<IdCard> isLongTermCustomer(Optional<IdCardSearchCriteria> criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(criteria)
                .map(IdCardSearchCriteria::getUploaderId, uploaderId -> builder.like(builder.upper(root.<String>get("uploaderId")), "%" + uploaderId + "%"))
                .map(IdCardSearchCriteria::getStatus, status -> builder.equal(root.<String>get("status"), status))
                .map(IdCardSearchCriteria::getUploaderCategory, uploadCat -> builder.equal(root.<String>get("uploaderCategory"), uploadCat))
                .map(IdCardSearchCriteria::getYear, year -> builder.between(root.<Date>get("createdDate"), new Date(year, 0, 1), new Date(year, 11, 31)))
                .getArray());
    }

}

