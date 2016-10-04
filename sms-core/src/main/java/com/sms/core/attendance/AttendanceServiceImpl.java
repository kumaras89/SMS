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

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

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
    private AttendanceDetailsRepository attendanceDetailsRepository;

    @Autowired
    public AttendanceServiceImpl(
            final AttendanceRepository attendanceRepository,
            final BranchRepository branchRepository,
            final UserRepository userRepository,
            final BatchRepository batchRepository,
            final SMSSenderDetailsGenerator SMSSenderDetailsGenerator,
            final MarketingEmployeeRepository marketingEmployeeRepository,
            final StudentRepository studentRepository,
            final StudentScholarRepository studentScholarRepository,
            final AttendanceDetailsRepository attendanceDetailsRepository) {
        this.attendanceRepository = attendanceRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.batchRepository = batchRepository;
        this.SMSSenderDetailsGenerator = SMSSenderDetailsGenerator;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.studentRepository = studentRepository;
        this.studentScholarRepository = studentScholarRepository;
        this.attendanceDetailsRepository = attendanceDetailsRepository;
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

        //for sending message(MARKETING_EMPLOYEE,PARENTS,GUARDIANS) who all are ABSENT AND LEAVE
        attendance.getAttendanceDetails()
                .stream()
                .filter(checkStatus -> checkStatus.getStatus().equals(AttendanceStatus.ABSENT.name()) || checkStatus.getStatus().equals(AttendanceStatus.LEAVE.name()))
                .forEach(checkStatus ->
                {
                    sendMessageWithContent(checkStatus.getStudentCode(), checkStatus.getStatus());
                });

        return savedStudents;
    }

    /**
     * @param applicationCode
     * @param attendanceStatus
     */
    private void sendMessageWithContent(String applicationCode, String attendanceStatus) {
        List<SendingDetails> sendingDetails = new ArrayList();

        Student student = studentRepository.findByCode(applicationCode);
        StudentScholar studentScholar = studentScholarRepository.findByApplicationNumberIgnoreCase(student.getScholarAppNo());
        MarketingEmployee marketingEmployee = marketingEmployeeRepository.findByCodeIgnoreCase(studentScholar.getMarketingEmployee().getCode());

        //set the content for marketing,student and parent
        String marketingEmployeeMessage = new StringBuilder("Hi,")
                .append(marketingEmployee.getName())
                .append(",Name:")
                .append(student.getName())
                .append(",Student Code :")
                .append(student.getCode())
                .toString();
        String studentMessage = new StringBuilder("Hi,")
                .append(student.getName())
                .append(",Student Code :")
                .append(student.getCode())
                .toString();
        String parentMessage = new StringBuilder("Hi,")
                .append(student.getName())
                .append(",Student Code :")
                .append(student.getCode())
                .toString();

        final BiFunction<String, String, Function<String, SendingDetails>> sendingDetailsCreator =
                (messageCode, message) -> phoneNumber ->
                        SendingDetails.builder().on(SendingDetails::getMessageCode).set(messageCode)
                                .on(SendingDetails::getSenderMessage).set(message)
                                .on(SendingDetails::getSenderPhoneNumber).set(phoneNumber)
                                .build();

        if (attendanceStatus.equals(AttendanceStatus.ABSENT.name())) {
            sendingDetails.add(sendingDetailsCreator.apply("SMS_STD_ABT", studentMessage).apply(studentScholar.getStudentPhoneNumber()));
            sendingDetails.add(sendingDetailsCreator.apply("SMS_PRT_ABT", parentMessage).apply(studentScholar.getParentPhoneNumber()));
            sendingDetails.add(sendingDetailsCreator.apply("SMS_EMP_ABT", marketingEmployeeMessage).apply(marketingEmployee.getPhoneNumber()));
        } else if (AttendanceStatus.LEAVE.name().equals(attendanceStatus)) {
            sendingDetails.add(sendingDetailsCreator.apply("SMS_STD_ALV", studentMessage).apply(studentScholar.getStudentPhoneNumber()));
            sendingDetails.add(sendingDetailsCreator.apply("SMS_PRT_ALV", parentMessage).apply(studentScholar.getParentPhoneNumber()));
        }

        SMSSenderDetailsGenerator.createSMSDetails(sendingDetails);
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
     * @param attendanceSearchCriteria
     * @return
     */
    @Override
    public List<AttendanceView> search(final AttendanceSearchCriteria attendanceSearchCriteria) {
        List<StudentAttendance> studentAttendances = AttendanceSearchService.search(attendanceSearchCriteria).with(attendanceRepository);
        List<AttendanceView> attendanceViews = new ArrayList<>();
        Iterator<StudentAttendance> iterator = studentAttendances.iterator();
        boolean checking = true;

        while (iterator.hasNext()) {
            StudentAttendance studentAttendance = iterator.next();
            Iterator<AttendanceDetails> attendanceDetailsList = studentAttendance.getAttendanceDetails().iterator();
            while (attendanceDetailsList.hasNext()) {
                AttendanceDetails attendanceDetails = attendanceDetailsList.next();
                List<Details> detailsList = new ArrayList<>();
                Details details = new Details();
                details.setId(attendanceDetails.getId());
                details.setStatus(attendanceDetails.getStatus());
                details.setDate(studentAttendance.getAttendanceDate());

                detailsList.add(details);

                AttendanceView attendanceView = new AttendanceView();
                attendanceView.setName(attendanceDetails.getStudentName());
                attendanceView.setCode(attendanceDetails.getStudentCode());
                attendanceView.setDateList(detailsList);

                Iterator<AttendanceView> checkingExist = attendanceViews.iterator();

                if (checkingExist.hasNext()) {
                    Iterator<AttendanceView> checkingExist1 = attendanceViews.iterator();
                    while (checkingExist1.hasNext()) {
                        AttendanceView attendanceView1 = checkingExist1.next();
                        if (attendanceView1.getCode().equals(attendanceView.getCode())) {
                            List<Details> existDetails = attendanceView1.getDateList();
                            existDetails.add(details);
                            attendanceView1.setDateList(existDetails);
                            checking = false;
                            break;
                        } else {
                            checking = true;
                        }
                    }
                    if (checking) {
                        attendanceViews.add(attendanceView);
                    }
                } else {
                    attendanceViews.add(attendanceView);
                }
            }
        }
        return attendanceViews;
    }

    /**
     * updating attendance details table by taking id and status
     *
     * @param id
     * @param status
     * @return
     */

    @Override
    public Optional<AttendanceDetails> update(final long id, final String status) {
        AttendanceDetails existDetails = attendanceDetailsRepository.findById(id);
        if (existDetails != null) {
            existDetails.setStatus(status);
            return Optional.of(attendanceDetailsRepository.saveAndFlush(existDetails));
        } else {
            throw new SmsException("Attendance Updation Error", "Update Details not available");
        }
    }
}