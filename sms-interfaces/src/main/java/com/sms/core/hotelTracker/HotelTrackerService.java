package com.sms.core.hotelTracker;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 */
public interface HotelTrackerService
{
    /**
     *
     * @param hotelTrackerInfo
     */
    void save(final HotelTrackerInfo hotelTrackerInfo);

    /**
     * @return
     */
    List<HotelTrackerInfo> findAll();

    /**
     * @param id
     * @return
     */
    Optional<HotelTrackerInfo> findById(Long id);

    /**
     *
     * @param hotelTrackerSearchCriteria
     * @return
     */

}
