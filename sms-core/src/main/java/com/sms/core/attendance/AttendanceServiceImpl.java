package com.sms.core.attendance;

import com.sms.core.SmsException;
import com.sms.core.common.FList;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.message.SMSSenderDetailsGenerator;
import com.sms.core.message.SendingDetails;
import com.sms.core.repositery.*;
import com.sms.core.student.Student;
import com.sms.core.student.StudentScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/29/2016.
 */
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private BranchRepository branchRepository;
    private UserRepository userRepository;
    private BatchRepository batchRepository;
    private SMSSenderDetailsGenerator SMSSenderDetailsGenerator;
    private StudentRepository studentRepository;
    private StudentScholarRepository studentScholarRepository;
    private MarketingEmployeeRepository marketingEmployeeRepository;

    @Autowired
    public AttendanceServiceImpl(
            final AttendanceRepository attendanceRepository,
            final BranchRepository branchRepository,
            final UserRepository userRepository,
            final BatchRepository batchRepository,
            final SMSSenderDetailsGenerator SMSSenderDetailsGenerator,
            final MarketingEmployeeRepository marketingEmployeeRepository,
            final StudentRepository studentRepository,
            final StudentScholarRepository studentScholarRepository) {
        this.attendanceRepository = attendanceRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.batchRepository = batchRepository;
        this.SMSSenderDetailsGenerator = SMSSenderDetailsGenerator;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.studentRepository = studentRepository;
        this.studentScholarRepository = studentScholarRepository;
    }

    private static StudentAttendanceInfo attendanceToInfo(final StudentAttendance source) {
        return StudentAttendanceInfo.toBuilder(source).build();
    }

    /**
     * for saving the attendance details
     *
     * @param entityType
     * @return
     */

    @Override
    public Optional<StudentAttendanceInfo> save(final StudentAttendanceInfo entityType) {

        final StudentAttendance attendance = StudentAttendance.toBuilder(entityType)
                .on(StudentAttendance::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                .on(StudentAttendance::getUser).set(userRepository.findByNameIgnoreCase(entityType.getUserName()))
                .on(StudentAttendance::getBatch).set(batchRepository.findByNameIgnoreCase(entityType.getBatchName()))
                .build();

        final Optional<StudentAttendanceInfo> savedStudents = Optional.of(attendanceRepository
                .saveAndFlush(attendance))
                .map(AttendanceServiceImpl::attendanceToInfo);
        /**
         * For sending message to the absents student in current date
         */
        for (AttendanceDetails attandanceDetails : attendance.getAttendanceDetails()) {
            if (attandanceDetails.getStatus()=="LEAVE") {

                List<SendingDetails> sendingDetailsList = new ArrayList();

                Student student = studentRepository.findByCode(attandanceDetails.getStudentCode());
                StudentScholar studentScholar = studentScholarRepository.findByApplicationNumberIgnoreCase(student.getScholarAppNo());
                MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentScholar.getMarketingEmployee().getCode());

                sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(student.getPhoneNumber())
                        .on(SendingDetails::getSenderMessage).set(
                                new StringBuilder("Hi")
                                        .append(student.getName())
                                        .append(",Application No:")
                                        .append(student.getCode())
                                        .toString())
                        .on(SendingDetails::getMessageCode).set("SMS_STUDENT_ABSENT")
                        .build());

                sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(studentScholar.getParentPhoneNumber())
                        .on(SendingDetails::getSenderMessage).set(
                                new StringBuilder("Hi")
                                        .append(studentScholar.getName())
                                        .append(",Application No:")
                                        .append(student.getCode())
                                        .toString())
                        .on(SendingDetails::getMessageCode).set("SMS_STUDENT_ABSENT")
                        .build());

                sendingDetailsList.add(SendingDetails.builder().on(SendingDetails::getSenderPhoneNumber).set(marketingEmployee.getPhoneNumber())
                        .on(SendingDetails::getSenderMessage).set(
                                new StringBuilder("Hi")
                                        .append(marketingEmployee.getName())
                                        .append(",Name:")
                                        .append(student.getName())
                                        .append(",Application No:")
                                        .append(student.getCode())
                                        .toString())
                        .on(SendingDetails::getMessageCode).set("SMS_MARKET_EMP_ABSENT")
                        .build());

                SMSSenderDetailsGenerator.createSMSDetails(sendingDetailsList);
            }
        }
        return savedStudents;
    }

    /**
     * @return
     */

    @Override
    public List<StudentAttendanceInfo> findAll() {
        return FList.of(this.attendanceRepository.findAll()).map(AttendanceServiceImpl::attendanceToInfo).get();
    }

    /**
     * @param id
     * @return
     */

    @Override
    public Optional<StudentAttendanceInfo> findById(final long id) {
        return Optional.of(this.attendanceRepository.findById(id)).map(AttendanceServiceImpl::attendanceToInfo);
    }

    /**
     * Update the Attendance Detail Using Date
     *
     * @param id
     * @param entityType
     * @return
     */

    @Override
    public Optional<StudentAttendanceInfo> update(final long id, final StudentAttendanceInfo entityType) {
        StudentAttendance alreadyExist = attendanceRepository.findById(id);
        if (alreadyExist != null) {
            final Optional<StudentAttendanceInfo> modifiedAttendance = Optional.of(attendanceRepository
                    .saveAndFlush(StudentAttendance.toBuilder(entityType)
                            .on(StudentAttendance::getBranch).set(branchRepository.findByCodeIgnoreCase(entityType.getBranchCode()))
                            .on(StudentAttendance::getUser).set(userRepository.findByNameIgnoreCase(entityType.getUserName()))
                            .on(StudentAttendance::getBatch).set(batchRepository.findByNameIgnoreCase(entityType.getBatchName()))
                            .on(StudentAttendance::getCreationDate).set(alreadyExist.getCreationDate())
                            .build()
                    ))
                    .map(AttendanceServiceImpl::attendanceToInfo);
            return modifiedAttendance;
        } else {
            throw new SmsException("Attendance Updation Error", "Updated One is not available");
        }
    }

    /**
     * @param attendanceSearchCriteria
     * @return
     */
    @Override
    public List<StudentAttendanceInfo> search(final AttendanceSearchCriteria attendanceSearchCriteria) {
        return AttendanceSearchService
                .search(attendanceSearchCriteria)
                .with(attendanceRepository);
    }
}