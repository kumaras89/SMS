package com.sms.core.admin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class Password {

    @NotEmpty(message = "Username is empty")
    private String userName;

    @NotEmpty(message = "Old password is empty")
    private String oldPassword;

    @NotEmpty(message = "New password is empty")
    @Length(min = 8, max = 15, message = "Password should be 8 to 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", message = "Password must have atleast one numeric, lower and uppercase and one special character(@#$%^&+=)")
    private String newPassword;

    public Password() {
    }

    public Password(String userName, String oldPassword, String newPassword) {
        this.userName = userName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
