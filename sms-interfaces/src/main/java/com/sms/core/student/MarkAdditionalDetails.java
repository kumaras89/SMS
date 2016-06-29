package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Ram on 6/20/2016.
 *
 */
@Embeddable
public class MarkAdditionalDetails {

    @NotNull(message = "Registration number is empty")
    @Size(min = 1, message = "Registration number is empty")
    @Column(name = "MS_REG_NUMBER")
    private String regNumber;

    @Min(value = 2000, message = "Year of passing is invalid")
    @Max(value = 2200, message = "Year of passing is invalid")
    @Column(name = "MS_YEAR_PASSING")
    private int yearOfPassing;

    @NotNull(message = "School Name is Empty")
    @Size(min = 1, message ="School name is Empty" )
    @Column(name = "MS_SCHOOL_NAME")
    private String schoolName;

    @NotNull(message = "Place of School is Empty")
    @Size(min = 1, message ="Place of School is Empty" )
    @Column(name = "MS_PLACE_SCHOOL")
    private String placeOfSchool;

    @NotNull(message = "Exam Board  Name is Empty")
    @Size(min = 1, message ="Exam Board name is Empty" )
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
