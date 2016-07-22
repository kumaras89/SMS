package com.sms.core.hotelHr;

import com.sms.core.SmsException;
import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.repositery.HotelHrRepository;
import com.sms.core.repositery.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/18/2016.
 * <p></p>
 */
@Service
@Transactional
public class HotelHrServiceImpl implements HotelHrService {
    private final HotelHrRepository hotelHrRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelHrServiceImpl(final HotelHrRepository hotelHrRepository,
                              final HotelRepository hotelRepository) {

        this.hotelHrRepository = hotelHrRepository;
        this.hotelRepository = hotelRepository;
    }

    private static HotelHrInfo hotelHrToInfo(final HotelHr source) {
        return HotelHrInfo.toBuilder(source).build();
    }

    @Override
    public Optional<HotelHrInfo> findById(final long hrId) {
        return Optional.of(this.hotelHrRepository.findById(hrId))
                       .map(HotelHrServiceImpl::hotelHrToInfo);
    }

    @Override
    public List<HotelHrInfo> findAll() {
        return FList.of(this.hotelHrRepository.findAll()).map(HotelHrServiceImpl::hotelHrToInfo).get();
    }

    @Override
    public Optional<HotelHrInfo> save(final HotelHrInfo entityType) {
        return Do.of(entityType)
                 .then(hotelHrInfo -> HotelHr.toBuilder(hotelHrInfo)
                     .on(HotelHr::getHotel)
                     .set(hotelRepository.findByHotelCodeIgnoreCase(entityType.getHotelCode()))
                     .build())
                 .then(hotelHrRepository::saveAndFlush)
                 .then(HotelHrServiceImpl::hotelHrToInfo)
                 .then(Optional::ofNullable).get();
    }

    @Override
    public Optional<HotelHrInfo> update(final Long id,final HotelHrInfo entityType) {
        return  Optional.ofNullable(hotelHrRepository.findById(id))
                        .map(hotelHr -> this.save(entityType))
                        .orElseThrow(
                            () ->
                                new SmsException("HotelHr Update Error",
                                    "What you trying to do Upadate its not available"));
    }
}
