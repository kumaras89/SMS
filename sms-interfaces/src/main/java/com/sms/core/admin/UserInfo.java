package com.sms.core.admin;

import com.sms.core.util.Builder;

import java.io.Serializable;


public class UserInfo implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;
    private String name;
    private String role;
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
