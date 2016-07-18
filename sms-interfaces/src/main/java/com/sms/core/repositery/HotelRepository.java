package com.sms.core.repositery;

import com.sms.core.hotel.Hotel;
import com.sms.core.hotel.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sathish on 7/14/2016.
 */
public interface HotelRepository extends JpaRepository<Hotel,String>
{
   Hotel findByHotelCode(String hotelCode);
}
