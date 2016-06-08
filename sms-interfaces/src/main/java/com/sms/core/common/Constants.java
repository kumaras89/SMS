package com.sms.core.common;

import com.sms.core.student.Relation;
import com.sms.core.student.StudentStatus;

public class Constants {

    private Caste[] caste;
    private Religion[] religion;
    private Gender[] gender;
    private MaritalStatus[] maritalStatus;
    private Rating[] rating;
    private Designation[] designation;
    private Relation[] relation;
    private StudentStatus[] studentStatuses;

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
}
