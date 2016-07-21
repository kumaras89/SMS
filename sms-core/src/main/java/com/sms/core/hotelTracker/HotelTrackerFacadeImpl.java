package com.sms.core.hotelTracker;

import com.sms.core.repositery.HotelTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sathish on 7/19/2016.
 */
@Service
@Transactional
public class HotelTrackerFacadeImpl implements HotelTrackerFacade
{
    private HotelTrackerRepository hotelTrackerRepository;

    @Autowired
    public HotelTrackerFacadeImpl(HotelTrackerRepository hotelTrackerRepository) {
        this.hotelTrackerRepository = hotelTrackerRepository;
    }

    @Override
    public List<HotelTrackerInfo> search(HotelTrackerSearchCriteria hotelTrackerSearchCriteria) {
        return HotelTrackerSearchService
                .search(hotelTrackerSearchCriteria)
                .with(hotelTrackerRepository);
    }
}
