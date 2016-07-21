package com.sms.core.repositery;

import com.sms.core.hotelTracker.HotelTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by sathish on 7/19/2016.
 */
public interface HotelTrackerRepository extends JpaRepository<HotelTracker,Long>, JpaSpecificationExecutor<HotelTracker>
{

}
