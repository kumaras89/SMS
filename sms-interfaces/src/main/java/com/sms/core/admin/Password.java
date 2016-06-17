package com.sms.core.admin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Password {

    @NotNull(message = "Username is empty")
    @Size(min = 1,message = "Username is empty")
    private String userName;

    @NotEmpty(message = "Old password is empty")
    private String oldPassword;

    @NotEmpty(message = "New password is empty")
    @Length(min = 8, max = 15, message = "Password should be 8 to 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", message = "Password must have atleast one numeric, lower and uppercase and one special character(@#$%^&+=)")
    private String newPassword;

    @NotEmpty(message = "Retype new password is empty")
    private String reTypeNewPwd;

    public Password() {
    }


    public Password(final String userName, final String oldPassword, final String newPassword, final String reTypeNewPwd) {
        this.userName = userName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.reTypeNewPwd = reTypeNewPwd;

    }

    public String getReTypeNewPwd() {
        return reTypeNewPwd;
    }

    public void setReTypeNewPwd(final String reTypeNewPwd) {
        this.reTypeNewPwd = reTypeNewPwd;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
