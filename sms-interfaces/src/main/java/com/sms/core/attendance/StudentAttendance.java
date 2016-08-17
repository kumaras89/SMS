package com.sms.core.attendance;

import com.sms.core.admin.User;
import com.sms.core.branch.Branch;
import com.sms.core.common.Builder;
import com.sms.core.course.Course;
import com.sms.core.student.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by sathish on 7/29/2016.
 */
@Entity
@Table(name = "SMS_TR_STUDENT_ATTENDANCE")
@SequenceGenerator(name = "SMS_SQ_AT", sequenceName = "SMS_SQ_AT", allocationSize = 1)
public class StudentAttendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_AT")
    private long id;

    @Column(name = "STA_ATTENDANCE_DATE", unique = true)
    private Date attendanceDate;

    @Column(name = "STA_BATCH")
    private int batch;

    @ManyToOne
    @JoinColumn(name = "STA_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "STA_BRANCH_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "STA_COURSE_ID")
    private Course course;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "AD_ATTANDANCE_ID")
    private Set<AttendanceDetails> attendanceDetails;

    @Column(name = "STA_CREATION_DATE")
    private Date creationDate;

    @Column(name = "STA_MODIFICATION_DATE")
    private Date modificationDate;

    public StudentAttendance() {
    }

    public static Builder<StudentAttendance> builder() {
        return Builder.of(StudentAttendance.class);
    }

    public static Builder<StudentAttendance> toBuilder(final StudentAttendanceInfo studentAttendanceInfo) {
        return builder()
                .on(StudentAttendance::getId).set(studentAttendanceInfo.getId())
                .on(StudentAttendance::getCreationDate).set(new Date())
                .on(StudentAttendance::getModificationDate).set(new Date())
                .on(StudentAttendance::getAttendanceDetails).set(studentAttendanceInfo.getAttendanceDetails())
                .on(StudentAttendance::getAttendanceDate).set(studentAttendanceInfo.getAttendanceDate())
                .on(StudentAttendance::getBatch).set(studentAttendanceInfo.getBatch());

    }

    public long getId() {
        return id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public User getUser() {
        return user;
    }

    public Branch getBranch() {
        return branch;
    }

    public Set<AttendanceDetails> getAttendanceDetails() {
        return attendanceDetails;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public Course getCourse() {
        return course;
    }

    public int getBatch() {
        return batch;
    }
}
