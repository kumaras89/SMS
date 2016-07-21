package com.sms.core.hotelTracker;

import java.util.List;

/**
 * Created by sathish on 7/19/2016.
 */
public interface HotelTrackerFacade
{
    List<HotelTrackerInfo> search(final HotelTrackerSearchCriteria hotelTrackerSearchCriteria);
}
