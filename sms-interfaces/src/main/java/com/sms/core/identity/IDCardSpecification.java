package com.sms.core.identity;

import org.springframework.data.jpa.domain.Specification;

public class IDCardSpecification {

    public static Specification<IdCard> isLongTermCustomer(IdCardSearchCriteria idCardSearchCriteria) {
        return (idCardRoot, query, builder) -> {
            return builder.or
                    (builder.like(builder.upper(idCardRoot.<String>get("uploaderId")), "%" + idCardSearchCriteria.getUploaderId() + "%"),
                            builder.or(builder.equal(idCardRoot.<String>get("status"), idCardSearchCriteria.getStatus()),
                                    builder.equal(idCardRoot.<String>get("uploaderCategory"), idCardSearchCriteria.getUploaderCategory())));
        };
    }
}

