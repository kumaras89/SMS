package com.sms.core.attendance;

import com.sms.core.common.*;
import com.sms.core.repositery.AttendanceRepository;
import com.sms.core.student.StudentSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/15/2016.
 */
public class AttendanceSearchService {
    public static Specification<StudentAttendance> attendanceSearchSpec(final AttendanceSearchCriteria criteria) {

        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
                .map(AttendanceSearchCriteria::getBatchName, name -> builder.like(builder.upper(root.join("batch", JoinType.LEFT).get("name")), "%" + name.toUpperCase() + "%"))
                .map(AttendanceSearchCriteria::getUserName, name -> builder.like(builder.upper(root.join("user", JoinType.LEFT).get("name")), "%" + name.toUpperCase() + "%"))
                .map(AttendanceSearchCriteria::getStudentName, name -> builder.like(builder.upper(root.join("attendanceDetails", JoinType.LEFT).get("studentName")), "%" + name.toUpperCase() + "%"))
                .map(AttendanceSearchCriteria::getBranchName, name -> builder.like(root.join("branch", JoinType.LEFT).get("name"), "%" + name + "%"))
                .map(AttendanceSearchCriteria::getDurationFrom, durationFrom -> builder.greaterThanOrEqualTo(root.<Date>get("attendanceDate"), durationFrom))
                .map(AttendanceSearchCriteria::getDurationTo, durationTo -> builder.lessThanOrEqualTo(root.<Date>get("attendanceDate"), durationTo))
                .getArray());
    }

    public static Reader<AttendanceRepository, List<StudentAttendanceInfo>> search(AttendanceSearchCriteria attendanceSearchCriteriaa) {
        return Reader.of(attendanceRepository ->
                Do.of(attendanceRepository)
                        .then(criteria -> attendanceSearchSpec(attendanceSearchCriteriaa))
                        .then(spec -> attendanceRepository.findAll(spec))
                        .then(attendances -> FList.of(attendances).map(aInfo -> StudentAttendanceInfo.toBuilder(aInfo).build()).get())
                        .get());
    }
}
