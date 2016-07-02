package com.sms.core.identity;

import com.sms.core.common.DateUtils;
import com.sms.core.common.PredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.Optional;

public class IDCardSpecification {

    public static Specification<IdCard> idCardRequest(final Optional<IdCardSearchCriteria> criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(criteria)
                .map(IdCardSearchCriteria::getIdentityCode, identityCode -> builder.like(builder.upper(root.<String>get("identityCode")), "%" + identityCode + "%"))
                .map(IdCardSearchCriteria::getStatus, status -> builder.equal(root.<String>get("status"), status))
                .map(IdCardSearchCriteria::getName, name -> builder.like(root.<String>get("name"), "%" + name + "%"))
                .map(IdCardSearchCriteria::getBranch, name -> builder.like(root.<String>get("branch"), "%" + name + "%"))
                .map(IdCardSearchCriteria::getYear, year -> builder.between(root.<Date>get("createdDate"),
                                                                    DateUtils.fromYear(Year.of(year), Month.JANUARY, 1),
                                                                    DateUtils.fromYear(Year.of(year), Month.DECEMBER, 31)))
                .getArray());
    }

}

