package com.sms.core.hotelTracker;

import com.sms.core.SmsException;
import com.sms.core.common.FList;
import com.sms.core.repositery.*;
import com.sms.core.scholarship.StudentScholarServiceImpl;
import com.sms.core.student.StudentScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 */

@Service
@Transactional
public class HotelTrackerServiceImpl implements HotelTrackerService
{
    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;
    private final HotelHrRepository hotelHrRepository;
    private final StudentRepository studentRepository;
    private final HotelTrackerRepository hotelTrackerRepository;
    @Autowired
    public HotelTrackerServiceImpl(final HotelRepository hotelRepository,final BranchRepository branchRepository,final HotelHrRepository hotelHrRepository,final StudentRepository studentRepository,final HotelTrackerRepository hotelTrackerRepository)
    {
        this.hotelRepository = hotelRepository;
        this.branchRepository = branchRepository;
        this.hotelHrRepository = hotelHrRepository;
        this.studentRepository = studentRepository;
        this.hotelTrackerRepository = hotelTrackerRepository;
    }

    private static HotelTrackerInfo trackerToInfo(HotelTracker source)
    {
        return HotelTrackerInfo.toBuilder(source).build();
    }


    @Override
    public void save(HotelTrackerInfo entityType)
    {
        HotelTracker tracker = HotelTracker.toBuilder(entityType)
                .on(HotelTracker::getBranchName).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .on(HotelTracker::getHotelName).set(hotelRepository.findByHotelCodeIgnoreCase(entityType.getHotelCode()))
                .on(HotelTracker::getHotelHrName).set(hotelHrRepository.findById(entityType.getHotelHrId()))
                .on(HotelTracker::getStudentName).set(studentRepository.findByCode(entityType.getStudentCode()))
                .build();

        hotelTrackerRepository.saveAndFlush(tracker);
    }

    @Override
    public List<HotelTrackerInfo> findAll()
    {
        return FList.of(this.hotelTrackerRepository.findAll()).map(HotelTrackerServiceImpl::trackerToInfo).get();
    }

    @Override
    public Optional<HotelTrackerInfo> findById(Long id) {
        return Optional.of(this.hotelTrackerRepository.findOne(id))
                .map(HotelTrackerServiceImpl::trackerToInfo);
    }
}
