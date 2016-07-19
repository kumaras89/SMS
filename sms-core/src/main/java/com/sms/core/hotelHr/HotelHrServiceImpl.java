package com.sms.core.hotelHr;

import com.sms.core.SmsException;
import com.sms.core.common.FList;
import com.sms.core.hotel.Hotel;
import com.sms.core.hotel.HotelInfo;
import com.sms.core.hotel.HotelServiceImpl;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.HotelHrRepository;
import com.sms.core.repositery.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/18/2016.
 */
@Service
@Transactional
public class HotelHrServiceImpl implements HotelHrService
{
    private final HotelHrRepository hotelHrRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelHrServiceImpl(final HotelHrRepository hotelHrRepository, final HotelRepository hotelRepository)
    {
        this.hotelHrRepository = hotelHrRepository;
        this.hotelRepository = hotelRepository;
    }

    private static HotelHrInfo hotelHrToInfo(HotelHr source)
    {
        return HotelHrInfo.toBuilder(source).build();
    }

    @Override
    public Optional<HotelHrInfo> findById(long hrId)
    {
        return Optional.of(this.hotelHrRepository.findById(hrId))
                .map(HotelHrServiceImpl::hotelHrToInfo);
    }

    @Override
    public List<HotelHrInfo> findAll()
    {
        return FList.of(this.hotelHrRepository.findAll()).map(HotelHrServiceImpl::hotelHrToInfo).get();
    }
    @Override
    public Optional<HotelHrInfo> save(HotelHrInfo entityType)
    {
        return Optional.of(hotelHrRepository.saveAndFlush
                (
                        HotelHr.toBuilder(entityType)
                                .on(HotelHr::getHotel)
                                .set(hotelRepository.findByHotelCodeIgnoreCase(entityType.getHotelCode()))
                                .build()
                )
        ).map(HotelHrServiceImpl::hotelHrToInfo);
    }
    @Override
    public Optional<HotelHrInfo> update(Long id, HotelHrInfo entityType) {
        HotelHr alreadyExist = hotelHrRepository.findById(id);
        if(alreadyExist!=null)
        {
            return save(entityType);
        }
        else {
            throw new SmsException("HotelHr Update Error", "What you trying to do Upadate its not available");
        }
    }
}
