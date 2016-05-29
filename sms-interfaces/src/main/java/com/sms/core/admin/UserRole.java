package com.sms.core.admin;

import com.sms.core.BaseModel;
import com.sms.core.util.Builder;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_MA_USER_ROLE")
public class UserRole extends BaseModel {

	@Column(name = "UR_NAME")
	private String name;
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
