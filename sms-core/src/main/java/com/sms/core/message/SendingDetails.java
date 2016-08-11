package com.sms.core.message;

import com.sms.core.common.Builder;

/**
 * Created by sathish on 8/11/2016.
 */
public class SendingDetails
{
    private String studentName;
    private String marketingEmployeeName;
    private String studentPhoneNumber;
    private String guardianPhoneNumber;
    private String marketingEmployeePhonenumber;
    private String studentApplicationNumber;

    public String getStudentName() {
        return studentName;
    }

    public String getMarketingEmployeeName() {
        return marketingEmployeeName;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public String getGuardianPhoneNumber() {
        return guardianPhoneNumber;
    }

    public String getMarketingEmployeePhonenumber() {
        return marketingEmployeePhonenumber;
    }

    public String getStudentApplicationNumber() {
        return studentApplicationNumber;
    }

    public static Builder<SendingDetails> builder(){
        return Builder.of(SendingDetails.class);
    }
}
