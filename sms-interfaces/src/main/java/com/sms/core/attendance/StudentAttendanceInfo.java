package com.sms.core.attendance;

import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.student.Student;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sathish on 7/29/2016.
 */
public class StudentAttendanceInfo {
    private long id;

    @NotNull(message = "Attendance Date is Empty")
    private Date attendanceDate;

    @NotNull(message = "User Name is Empty")
    private String userName;

    @NotNull(message = "Branch Name is Empty")
    private String branchCode;

    @NotNull(message = "Course Name is empty")
    private String courseCode;

    @NotNull(message = "Batch is Empty")
    private int batch;

    @Valid
    @NotNull(message = "Attendance Details is Empty")
    private Set<AttendanceDetails> attendanceDetails;

    private Date creationDate;

    private Date modificationDate;

    public StudentAttendanceInfo() {

    }

    public static Builder<StudentAttendanceInfo> builder() {
        return Builder.of(StudentAttendanceInfo.class);
    }

    public static Builder<StudentAttendanceInfo> toBuilder(final StudentAttendance studentAttendance) {
        return builder()
                .on(StudentAttendanceInfo::getId).set(studentAttendance.getId())
                .on(StudentAttendanceInfo::getModificationDate).set(studentAttendance.getModificationDate())
                .on(StudentAttendanceInfo::getCreationDate).set(studentAttendance.getCreationDate())
                .on(StudentAttendanceInfo::getBranchCode).set(studentAttendance.getBranch().getCode())
                .on(StudentAttendanceInfo::getUserName).set(studentAttendance.getUser().getName())
                .on(StudentAttendanceInfo::getAttendanceDate).set(studentAttendance.getAttendanceDate())
                .on(StudentAttendanceInfo::getCourseCode).set(studentAttendance.getCourse().getName())
                .on(StudentAttendanceInfo::getAttendanceDetails).set(studentAttendance.getAttendanceDetails())
                .on(StudentAttendanceInfo::getBatch).set(studentAttendance.getBatch())
                ;
    }

    public long getId() {
        return id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getBranchCode() {
        return branchCode;
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

    public String getCourseCode() {
        return courseCode;
    }

    public int getBatch() {
        return batch;
    }
}
