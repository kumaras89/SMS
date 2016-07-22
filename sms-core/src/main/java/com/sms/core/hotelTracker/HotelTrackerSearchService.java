package com.sms.core.hotelTracker;

import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.common.PredicateBuilder;
import com.sms.core.common.Reader;
import com.sms.core.repositery.HotelTrackerRepository;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 * <p></p>
 */
public class HotelTrackerSearchService {
    public static Specification<HotelTracker> hotelSearchSpec(final HotelTrackerSearchCriteria criteria) {


        return (root, query, builder) -> builder.and(PredicateBuilder.of(Optional.ofNullable(criteria))
            .map(
                HotelTrackerSearchCriteria::getStudentName,
                name -> builder.like(root.join("studentName", JoinType.LEFT).get("name"), "%" + name + "%"))
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
