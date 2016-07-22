package com.sms.core.hotelTracker;

import java.util.List;

/**
 * Created by sathish on 7/19/2016.
 * <p></p>
 */
public interface HotelTrackerFacade {
    /**
     *
     * @param hotelTrackerSearchCriteria
     * @return
     */
    List<HotelTrackerInfo> search(final HotelTrackerSearchCriteria hotelTrackerSearchCriteria);
}
