package com.sms.core.repositery;

import com.sms.core.hotel.Hotel;
import com.sms.core.hotelHr.HotelHr;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sathish on 7/18/2016.
 */

public interface HotelHrRepository extends JpaRepository<HotelHr,Long>
{
        HotelHr findById(long hotelId);
}
