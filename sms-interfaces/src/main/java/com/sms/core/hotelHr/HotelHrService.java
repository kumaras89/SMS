package com.sms.core.hotelHr;

import com.sms.core.hotel.HotelInfo;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/18/2016.
 */
public interface HotelHrService
{

    Optional<HotelHrInfo> findById(long id);

    /**
     * @return
     */
    List<HotelHrInfo> findAll();

    /**
     * @param entityType
     * @return
     */
    Optional<HotelHrInfo> save(final HotelHrInfo entityType);

    /**
     *
     * @param id
     * @param entityType
     * @return
     */

    Optional<HotelHrInfo> update(final Long id, final HotelHrInfo entityType);
}
