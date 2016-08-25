package com.sms.core.repositery;


import com.sms.core.hotelTracker.HotelTracker;
import com.sms.core.hotelTracker.HotelTrackerInfo;
import com.sms.core.hotelTracker.HotelTrackerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 */
public interface HotelTrackerRepository extends JpaRepository<HotelTracker,Long>, JpaSpecificationExecutor<HotelTracker>
{
    HotelTracker findById(long id);

    @Query("select hotelTracker from HotelTracker hotelTracker where hotelTracker.studentName.id = :studentId and hotelTracker.status = :status ")
    HotelTracker findByUserName(@Param("studentId") final long studentId, @Param("status") final HotelTrackerStatus status);

    @Modifying
    @Query( value = "update HotelTracker hotelTracker set hotelTracker.status = :status, hotelTracker.modifiedDate= :modifiedDate  where hotelTracker.hotelTrackerCode = :hotelTrackerCode")
    int updateStatus(@Param("status") final HotelTrackerStatus status, @Param("modifiedDate") final Date modifiedDate , @Param("hotelTrackerCode") final String hotelTrackerCode);

}
