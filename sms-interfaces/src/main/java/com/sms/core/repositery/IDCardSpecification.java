package com.sms.core.repositery;

import com.sms.core.identity.IdCard;
import com.sms.core.identity.IdCardSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IDCardSpecification {

    public static Specification<IdCard> isLongTermCustomer(IdCardSearchCriteria idCardSearchCriteria) {
        return new Specification<IdCard>() {
            public Predicate toPredicate(Root<IdCard> idCardRoot, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.or
                        (builder.like(builder.upper(idCardRoot.<String>get("uploaderId")), "%" + idCardSearchCriteria.getUploaderId() + "%"),
                                builder.or(builder.equal(idCardRoot.<String>get("status"), idCardSearchCriteria.getStatus()),
                                        builder.equal(idCardRoot.<String>get("uploaderCategory"), idCardSearchCriteria.getUploaderCategory())));
            }
        };
    }

}
