package com.sms.core.admin;

public interface UserConverter {
	
	User convert(final UserInfo userInfo);

	UserInfo convert(final User user);
}
