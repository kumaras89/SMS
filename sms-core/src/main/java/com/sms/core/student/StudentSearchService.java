package com.sms.core.student;

import com.sms.core.common.*;
import com.sms.core.repositery.StudentRepository;
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
public class StudentSearchService {

    public static Specification<Student> idCardRequest(final StudentSearchCriteria criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
                .map(StudentSearchCriteria::getStudentName, identityCode -> builder.like(builder.upper(root.<String>get("name")), "%" + identityCode.toUpperCase() + "%"))
                .map(StudentSearchCriteria::getStudentCode, status -> builder.equal(root.<String>get("code"), status))
                .map(StudentSearchCriteria::getBranchName, name -> builder.like(root.join("branch",  JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(StudentSearchCriteria::getYear, year -> builder.between(root.<Date>get("createdDate"),
                        DateUtils.fromYear(Year.of(year), Month.JANUARY, 1),
                        DateUtils.fromYear(Year.of(year), Month.DECEMBER, 31)))
                .getArray());
    }

    public static Reader<StudentRepository, List<StudentInfo>> search(StudentSearchCriteria studentSearchCriteria) {
        return Reader.of(studentRepo ->
            Do.of(studentSearchCriteria)
                    .then(criteria -> idCardRequest(criteria))
                    .then(spec -> studentRepo.findAll(spec))
                    .then(studs -> FList.of(studs).map(StudentEnrollmentConverter::convertTo).get())
                    .get());
    }
}
