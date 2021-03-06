package com.sms.core.hotel;

import com.sms.core.student.StudentScholarInfo;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/15/2016.
 */
public interface HotelService
{

    Optional<HotelInfo> findById(long hotelId);

    /**
     * @return
     */
    List<HotelInfo> findAll();

    /**
     * @param entityType
     * @return
     */
    Optional<HotelInfo> save(final HotelInfo entityType);

    /**
     *
     * @param id
     * @param entityType
     * @return
     */

    Optional<HotelInfo> update(final Long id, final HotelInfo entityType);
}
