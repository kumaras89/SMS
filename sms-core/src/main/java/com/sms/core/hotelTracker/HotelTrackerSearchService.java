package com.sms.core.hotelTracker;

import com.sms.core.common.*;
import com.sms.core.repositery.HotelTrackerRepository;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.name -> builder.like(builder.upper(root.<String>get("name")), "%" + name.toUpperCase() + "%"
 * <p></p>
 */
public class HotelTrackerSearchService {

    public static Specification<HotelTracker> hotelSearchSpec(final HotelTrackerSearchCriteria criteria)
    {
        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
            .map(
                HotelTrackerSearchCriteria::getStudentName,
                name -> builder.like(builder.upper(root.join("studentName", JoinType.LEFT).get("name")), "%" + name.toUpperCase() + "%"))
            .map(HotelTrackerSearchCriteria::getStatus, status -> builder.equal(root.<String>get("status"), status))
            .map(
                HotelTrackerSearchCriteria::getHotelHrName,
                name -> builder.like(root.join("hotelHrName", JoinType.LEFT).get("name"), "%" + name + "%"))
            .map(
                HotelTrackerSearchCriteria::getHotelName,
                name -> builder.like(root.join("hotelName", JoinType.LEFT).get("hotelName"), "%" + name + "%"))
            .map(
                HotelTrackerSearchCriteria::getBranchName,
                name -> builder.like(root.join("branchName", JoinType.LEFT).get("name"), "%" + name + "%"))
            .map(HotelTrackerSearchCriteria::getDurationFrom, fromDate -> builder.greaterThanOrEqualTo(root.<Date>get("durationFrom"),fromDate))
            .map(HotelTrackerSearchCriteria::getDurationTo, toDate -> builder.lessThanOrEqualTo(root.<Date>get("durationTo"),toDate))
                .getArray());
    }
    public static Reader<HotelTrackerRepository, List<HotelTrackerInfo>> search(final HotelTrackerSearchCriteria
                                                                                           hotelTrackerSearchCriteria) {
        return Reader.of(hotelTrackRepo -> Do.of(hotelTrackerSearchCriteria)
                                             .then(criteria -> hotelSearchSpec(criteria))
                                             .then(spec -> hotelTrackRepo.findAll(spec))
                                             .then(hotel ->
                                                 FList.of(hotel)
                                                 .map(hInfo -> HotelTrackerInfo.
                                                                           toBuilder(hInfo)
                                                                     .build())
                                                 .get())
                                             .get());
    }
}
