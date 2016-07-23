package com.sms.core.hotelTracker;

import com.sms.core.SmsException;
import com.sms.core.common.Do;
import com.sms.core.common.FList;
import com.sms.core.repositery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 * <p></p>
 */
@Service
@Transactional
public class HotelTrackerServiceImpl implements HotelTrackerService {

    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;
    private final HotelHrRepository hotelHrRepository;
    private final StudentRepository studentRepository;
    private final HotelTrackerRepository hotelTrackerRepository;

    @Autowired
    public HotelTrackerServiceImpl(
        final HotelRepository hotelRepository,
        final BranchRepository branchRepository,
        final HotelHrRepository hotelHrRepository,
        final StudentRepository studentRepository,
        final HotelTrackerRepository hotelTrackerRepository) {

        this.hotelRepository = hotelRepository;
        this.branchRepository = branchRepository;
        this.hotelHrRepository = hotelHrRepository;
        this.studentRepository = studentRepository;
        this.hotelTrackerRepository = hotelTrackerRepository;
    }

    private static HotelTrackerInfo trackerToInfo(final HotelTracker source) {
        return HotelTrackerInfo.toBuilder(source).build();
    }
    private static HotelTracker infoToTracker(final HotelTrackerInfo target) {
        return HotelTracker.toBuilder(target).build();
    }

    @Override
    public Optional<HotelTrackerInfo> save(final HotelTrackerInfo hotelTrackerInfo) {

        HotelTracker hotelTrackers = hotelTrackerRepository.findByUserName(studentRepository.findByCode(hotelTrackerInfo.getStudentCode()).getId(),HotelTrackerStatus.valueOf(hotelTrackerInfo.getStatus()));

        if(hotelTrackers!=null)
        {
            throw new SmsException("Insertion Error","Which Student You are mapping that is already mapped.");
        }
        else
        {
            return this.CommonSave(hotelTrackerInfo);
        }
    }
    public Optional<HotelTrackerInfo> CommonSave(final HotelTrackerInfo hotelTrackerInfo)
    {
        return Optional.ofNullable(
                Do.of(hotelTrackerInfo)
                        .then(hotelInfo -> HotelTracker.toBuilder(hotelTrackerInfo)
                                .on(HotelTracker::getBranchName).set(
                                        branchRepository.findByCodeIgnoreCase(hotelTrackerInfo.getBranchCode()))
                                .on(HotelTracker::getHotelName).set(hotelRepository.findByHotelCodeIgnoreCase(hotelTrackerInfo.getHotelCode()))
                                .on(HotelTracker::getHotelHrName).set(hotelHrRepository.findById(hotelTrackerInfo.getHotelHrId()))
                                .on(HotelTracker::getStudentName).set(studentRepository.findByCode(hotelTrackerInfo.getStudentCode()))
                                .build())
                        .then(hotelTracker -> hotelTrackerRepository.saveAndFlush(hotelTracker))
                        .then(HotelTrackerServiceImpl::trackerToInfo).get()
        );
    }

    @Override
    public List<HotelTrackerInfo> findAll() {
        return FList.of(this.hotelTrackerRepository.findAll()).map(HotelTrackerServiceImpl::trackerToInfo).get();
    }



    @Override
    public Optional<HotelTrackerInfo> findById(final Long id) {
        return Optional.of(this.hotelTrackerRepository.findOne(id)).map(HotelTrackerServiceImpl::trackerToInfo);
    }

    @Override
    public Optional<HotelTrackerInfo> update(Long id, final HotelTrackerInfo entityType) {
        return Optional.ofNullable(hotelTrackerRepository.findById(id))
                .map(tracker -> this.CommonSave(entityType))
                .orElseThrow(() ->  new SmsException("HotelTracker Update Error", "What you trying to do Update its not available"));
    }
}
