package com.sms.core.repositery;

import com.sms.core.attendance.AttendanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("InterfaceNeverImplemented")
public interface AttendanceDetailsRepository extends JpaRepository<AttendanceDetails, String> {
    AttendanceDetails findById(final long id);
}
