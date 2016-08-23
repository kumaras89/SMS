package com.sms.core.common;

import com.sms.core.attendance.AttendanceStatus;
import com.sms.core.hotelTracker.HotelTrackerStatus;
import com.sms.core.identity.IdCardStatus;
import com.sms.core.scholarship.ScholarStatus;
import com.sms.core.student.Relation;
import com.sms.core.student.StudentStatus;

public class Constants {


    public Caste[] getCaste() {
        return Caste.values();
    }

    public Religion[] getReligion() {
        return Religion.values();
    }

    public Gender[] getGender() {
        return Gender.values();
    }

    public MaritalStatus[] getMaritalStatus() {
        return MaritalStatus.values();
    }

    public Rating[] getRating() {
        return Rating.values();
    }

    public Designation[] getDesignation() {
        return Designation.values();
    }

    public Relation[] getRelation() {
        return Relation.values();
    }

    public StudentStatus[] getStudentStatuses() {
        return StudentStatus.values();
    }

    public IdCardStatus[] getIdCardStatuses() {
        return IdCardStatus.values();
    }

    public ScholarStatus[] getScholarStatuses() {
        return ScholarStatus.values();
    }

    public HotelTrackerStatus [] getHotelTrackerStatus() {return HotelTrackerStatus.values();}

    public AttendanceStatus[] getAttendanceStatus() {return AttendanceStatus.values();}
}
