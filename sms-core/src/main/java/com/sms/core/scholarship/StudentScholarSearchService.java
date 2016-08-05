package com.sms.core.scholarship;

import com.sms.core.common.*;
import com.sms.core.repositery.StudentScholarRepository;
import com.sms.core.student.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ganesan on 02/07/16.
 */
public class StudentScholarSearchService {

    public static Specification<StudentScholar> scholarSearchSpec(final StudentScholarSearchCriteria criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
                .map(StudentScholarSearchCriteria::getStudentName, name -> builder.like(builder.upper(root.<String>get("name")), "%" + name.toUpperCase() + "%"))
                .map(StudentScholarSearchCriteria::getStatus, status -> builder.equal(root.<String>get("status"), status))
                .map(StudentScholarSearchCriteria::getMarketingEmployeeName, name -> builder.like(root.join("marketingEmployee",  JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(StudentScholarSearchCriteria::getBranchName, name -> builder.like(root.join("branch",  JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(StudentScholarSearchCriteria::getYear, year -> builder.between(root.<Date>get("createdDate"),
                        DateUtils.fromYear(Year.of(year), Month.JANUARY, 1),
                        DateUtils.fromYear(Year.of(year), Month.DECEMBER, 31)))
                .getArray());
    }

    public static Reader<StudentScholarRepository, List<StudentScholarInfo>> search(StudentScholarSearchCriteria studentSearchCriteria) {
        return Reader.of(studentRepo ->
                Do.of(studentSearchCriteria)
                        .then(criteria -> scholarSearchSpec(criteria))
                        .then(spec -> studentRepo.findAll(spec))
                        .then(studs -> FList.of(studs).map(sInfo -> StudentScholarInfo.toBuilder(sInfo).build()).get())
                        .get());
    }
}
