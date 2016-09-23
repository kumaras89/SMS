package com.sms.core.attendance;

import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "SMS_TR_ATTENDANCE_DETAILS")
@SequenceGenerator(name = "SMS_SQ_ATD", sequenceName = "SMS_SQ_ATD", allocationSize = 1)
public class AttendanceDetails implements Serializable {

    private static final long serialVersionUID = -6890590290096608469L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_ATD")
    private Long id;

    @Column(name = "AD_STUDENT_NAME")
    @NotNull(message = "Student Name is Empty")
    private String studentName;

    @Column(name = "AD_STUDENT_CODE")
    @NotNull(message = "Student Code is Empty")
    private String studentCode;

    @Column(name = "AD_ATTENDANCE_STATUS")
    @NotNull(message = "Attendance Status is Empty")
    private String status;

    public AttendanceDetails() {
        super();
    }

    public static Builder<AttendanceDetails> builder() {
        return Builder.of(AttendanceDetails.class);
    }

    public Long getId() {
        return id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}