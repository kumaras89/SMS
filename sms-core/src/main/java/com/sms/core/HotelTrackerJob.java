package com.sms.core;

import com.sms.core.common.DateUtils;
import com.sms.core.hotelTracker.HotelTracker;
import com.sms.core.hotelTracker.HotelTrackerStatus;
import com.sms.core.repositery.HotelTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@EnableScheduling
@Transactional
public class HotelTrackerJob {
    @Autowired
    private HotelTrackerRepository hotelTrackerRepository;

    @Scheduled(cron = "0 0 23 * * *")
    public void checkHotelTrackerStatus() {
        hotelTrackerRepository
            .findAll((root, query, cb) -> cb.equal(root.<String>get("status"), HotelTrackerStatus.MAPPED))
            .stream()
            .filter(hotelTracker -> DateUtils.isDateCrossed(hotelTracker.getDurationTo()))
            .forEach(hotelTracker -> {
                    updateStatus(hotelTracker);
                }
            );
    }

    private void updateStatus(final HotelTracker hotelTracker) {
        hotelTrackerRepository.updateStatus(
            HotelTrackerStatus.COMPLETED,
            new Date(),
            hotelTracker.getHotelTrackerCode());
    }
}
