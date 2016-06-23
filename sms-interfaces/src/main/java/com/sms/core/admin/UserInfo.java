package com.sms.core.admin;

import com.sms.core.common.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserInfo implements Serializable {

	private Long id;

    @NotNull(message = "First name is empty")
    @Size(min = 1, message = "First name is empty")
	private String firstName;

    @NotNull(message = "Last name is empty")
    @Size(min = 1, message = "Last name is empty")
	private String lastName;

    @NotNull(message = "User Name  is empty")
    @Size(min = 1, message = "User Name  is empty")
    private String name;

    @NotNull(message = "Role  is empty")
    @Size(min = 1, message = "Role  is empty")
    private String role;

    @NotNull(message = "Branch  is empty")
    @Size(min = 1, message = "Branch is empty")
    private String branch;

    public UserInfo() {

    }


    public static Builder<UserInfo> builder() {
        return Builder.of(UserInfo.class);
    }

    public static Builder<UserInfo> toBuilder(final User user) {
        return builder()
                .on(u -> u.getName()).set(user.getName())
                .on(u-> u.getRole()).set(user.getRole().getName())
                .on(u-> u.getBranch()).set(user.getBranch())
                .on(u-> u.getFirstName()).set(user.getFirstName())
                .on(u-> u.getLastName()).set(user.getLastName())
                .on(u-> u.getId()).set(user.getId());

    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }    
    
    public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


    public String getBranch() {
        return branch;
    }
}
