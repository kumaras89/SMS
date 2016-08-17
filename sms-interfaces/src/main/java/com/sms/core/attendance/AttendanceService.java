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
     * @param studentAttendanceInfo
     * @return
     */

    Optional<StudentAttendanceInfo> update(final long id, final StudentAttendanceInfo studentAttendanceInfo);

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

    List<StudentAttendanceInfo> search(final AttendanceSearchCriteria attendanceSearchCriteria);
}
