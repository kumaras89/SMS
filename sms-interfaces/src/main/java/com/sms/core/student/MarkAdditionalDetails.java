package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Ram on 6/20/2016.
 *
 */
@Embeddable
public class MarkAdditionalDetails {

    @Column(name = "MS_REG_NUMBER")
    private String regNumber;
    @Column(name = "MS_YEAR_PASSING")
    private int yearOfPassing;
    @Column(name = "MS_SCHOOL_NAME")
    private String schoolName;
    @Column(name = "MS_PLACE_SCHOOL")
    private String placeOfSchool;
    @Column(name = "MS_BOARD_OF_EXAMINATION")
    private String boardOfExamination;

    public String getRegNumber() {
        return regNumber;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getPlaceOfSchool() {
        return placeOfSchool;
    }

    public String getBoardOfExamination() {
        return boardOfExamination;
    }

    public static Builder<MarkAdditionalDetails> builder() {
        return Builder.of(MarkAdditionalDetails.class);
    }
}
