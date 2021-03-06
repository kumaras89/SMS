package com.sms.core.hotelTracker;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 * <p></p>
 */
public interface HotelTrackerService {

    /**
     * @param hotelTrackerInfo
     */
    Optional<HotelTrackerInfo> save(final HotelTrackerInfo hotelTrackerInfo);

    /**
     * @return
     */
    List<HotelTrackerInfo> findAll();

    /**
     * @param id
     * @return
     */
    Optional<HotelTrackerInfo> findById(Long id);


    Optional<HotelTrackerInfo> update(final Long id, final HotelTrackerInfo hotelTrackerInfo);

    List<HotelTrackerInfo> search(final HotelTrackerSearchCriteria hotelTrackerSearchCriteria);

}
