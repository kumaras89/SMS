package com.sms.core.hotel;

import com.sms.core.SmsException;
import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/15/2016.
 * <p></>
 */
@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepository hotelRepository, final BranchRepository branchRepository) {
        this.hotelRepository = hotelRepository;
        this.branchRepository = branchRepository;
    }

    private static HotelInfo hotelToInfo(final Hotel source) {
        return HotelInfo.toBuilder(source).build();
    }

    @Override
    public Optional<HotelInfo> findById(final long hotelId) {
        return Optional.of(this.hotelRepository.findById(hotelId)).map(HotelServiceImpl::hotelToInfo);
    }

    @Override
    public List<HotelInfo> findAll() {
        return FList.of(this.hotelRepository.findAll()).map(HotelServiceImpl::hotelToInfo).get();
    }

    @Override
    public Optional<HotelInfo> save(final HotelInfo entityType) {
        return Optional.ofNullable(
                 Do.of(entityType)
                 .then(hotelInfo -> Hotel.toBuilder(entityType)
                                             .on(Hotel::getBranch).set(
                                                     branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                                         .build())
                 .then(hotel -> hotelRepository.saveAndFlush(hotel))
                 .then(HotelServiceImpl::hotelToInfo).get()
        );
    }

    @Override
    public Optional<HotelInfo> update(final Long id, final HotelInfo entityType) {
      return Optional.ofNullable(hotelRepository.findById(id))
                              .map(hotel -> this.save(entityType))
                              .orElseThrow(() ->  new SmsException("Hotel Update Error", "What you trying to do Update its not available"));
    }
}
