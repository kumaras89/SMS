package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_MA_USER")
public class User extends BaseModel {


	@Column(name = "US_FIRST_NAME")
	private String firstName;


	@Column(name = "US_LAST_NAME")
	private String lastName;


	@Column(name = "US_BRANCH")
	private String branch;


	@Column(name = "US_NAME")
	private String name;



	@ManyToOne
	@JoinColumn(name = "US_UR_ID")
	private UserRole role;


	@Column(name = "US_PASSWORD")
	private String password;

	public User() {
		super();
	}

	public static Builder<User> builder() {
		return Builder.of(User.class);
	}

	public static Builder<User> toBuilder(final User user) {

		return builder()
				.on(u -> u.getId()).set(user.getId())
				.on(u -> u.getName()).set(user.getName())
				.on(u -> u.getRole()).set(user.getRole())
				.on(u -> u.getPassword()).set(user.getPassword())
				.on(u -> u.getBranch()).set(user.getBranch())
				.on(u -> u.getFirstName()).set(user.getFirstName())
				.on(u -> u.getLastName()).set(user.getLastName());

	}

	public String getName() {
		return name;
	}

	public UserRole getRole() {
		return role;
	}

	public String getPassword() {
		return password;
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
