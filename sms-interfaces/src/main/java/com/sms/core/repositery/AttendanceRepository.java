package com.sms.core.repositery;

import com.sms.core.attendance.StudentAttendance;
import org.apache.commons.dbcp.AbandonedTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

/**
 * Created by sathish on 7/29/2016.
 */
public interface AttendanceRepository extends JpaRepository<StudentAttendance, String>, JpaSpecificationExecutor<StudentAttendance> {
    StudentAttendance findById(long id);

}
