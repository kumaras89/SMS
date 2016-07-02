package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SMS_MA_USER_ROLE")
public class UserRole extends BaseModel {

	@NotNull(message = "Role  Name is empty")
	@Size(min = 1, message = "Role Name is empty")
	@Column(name = "UR_NAME",unique = true)
	private String name;

	@NotNull(message = "Role Desc is empty")
	@Size(min = 1, message = "Role Desc is empty")
	@Column(name = "UR_DESC")
	private String desc;


	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}


	public static Builder<UserRole> builder() {
		return Builder.of(UserRole.class);
	}

}
