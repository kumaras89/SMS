package com.sms.core.repositery;

import com.sms.core.attendance.AttendanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Assaycr-04 on 8/25/2016.
 */
public interface AttendanceDetailsRepository  extends JpaRepository<AttendanceDetails,String>
{
    AttendanceDetails findById(final long id);
}
