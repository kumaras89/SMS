package com.sms.core.hotel;

import com.sms.core.IStudentPortalService;
import com.sms.core.SmsException;
import com.sms.core.branch.Branch;
import com.sms.core.common.FList;
import com.sms.core.repositery.BranchRepository;
import com.sms.core.repositery.HotelRepository;
import com.sms.core.scholarship.StudentScholarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/15/2016.
 */
@Service
@Transactional
public class HotelServiceImpl implements HotelService
{
    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepository hotelRepository,final BranchRepository branchRepository)
    {
        this.hotelRepository = hotelRepository;
        this.branchRepository = branchRepository;
    }

    private static HotelInfo hotelToInfo(Hotel source)
    {
        return HotelInfo.toBuilder(source).build();
    }

    @Override
    public Optional<HotelInfo> findById(long hotelId)
    {
        return Optional.of(this.hotelRepository.findById(hotelId))
                .map(HotelServiceImpl::hotelToInfo);
    }

    @Override
    public List<HotelInfo> findAll()
    {
        return FList.of(this.hotelRepository.findAll()).map(HotelServiceImpl::hotelToInfo).get();
    }
    @Override
    public Optional<HotelInfo> save(HotelInfo entityType)
    {
        return Optional.of(hotelRepository.saveAndFlush
                (
                        Hotel.toBuilder(entityType)
                        .on(Hotel::getBranch)
                        .set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                        .build()
                )
                ).map(HotelServiceImpl::hotelToInfo);
    }

    @Override
    public Optional<HotelInfo> update(Long id, HotelInfo entityType) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel!=null)
        {
            return save(entityType);
        }
        else {
            throw new SmsException("Hotel Update Error", "What you trying to do Upadate its not available");
        }
    }
}
