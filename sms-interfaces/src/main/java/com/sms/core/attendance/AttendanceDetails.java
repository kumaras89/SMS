package com.sms.core.attendance;

import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sathish on 7/29/2016.
 */

@Entity
@Table(name = "SMS_TR_ATTENDANCE_DETAILS")
@SequenceGenerator(name = "SMS_SQ_ATD",sequenceName = "SMS_SQ_ATD", allocationSize = 1)
public class AttendanceDetails implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_ATD")
    private long id;

    @Column(name = "AD_STUDENT_NAME")
    @NotNull(message = "Student Name is Empty")
    private String studentName;

    @Column(name = "AD_STUDENT_CODE")
    @NotNull(message = "Student Code is Empty")
    private String studentCode;

    @Column(name = "AD_ATTENDANCE_STATUS")
    @NotNull(message = "Student Present is Empty")
    private boolean present;

    @Column(name = "AD_ATTANDANCE_ID")
    private long attendanceId;

    public AttendanceDetails() {
        super();
    }

    public static Builder<AttendanceDetails> builder() {
        return Builder.of(AttendanceDetails.class);
    }

    public long getId() {
        return id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public boolean isPresent() {
        return present;
    }

    public long getAttendanceId() {
        return attendanceId;
    }
}
