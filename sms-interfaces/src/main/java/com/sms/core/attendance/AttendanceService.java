package com.sms.core.attendance;


import com.sms.core.student.StudentScholarInfo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/29/2016.
 */
public interface AttendanceService
{
    /**
     *
     * @param entityType
     * @return
     */

    Optional<StudentAttendanceInfo> save(final StudentAttendanceInfo entityType);
    /**
     *
     * @param id
     * @return
     */
    Optional<StudentAttendanceInfo> findById(final long id);

    /**
     *
     * @return
     */

    List<StudentAttendanceInfo> findAll();

    /**
     *
     * @param attendanceSearchCriteria
     * @return
     */

    List<AttendanceView> search(final AttendanceSearchCriteria attendanceSearchCriteria);

    /**
     *This update for doing update the Attendance Details rather than attendance main Table
     * @param id
     * @param status
     */

    Optional<AttendanceDetails> update(final long id, final String status);
}
