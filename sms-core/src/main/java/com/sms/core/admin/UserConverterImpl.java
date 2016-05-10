package com.sms.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

	@Autowired
	private PasswordEncoder encoder;

	@Value("${DEFAULT_PASSWORD}")
	private String defaultPassword;

	@Override
	public User convert(final UserInfo userInfo) {
		final User user = new User();
		user.setName(userInfo.getName());
		user.setRole(UserRole.valueOf(userInfo.getRole().toUpperCase()));
		user.setPassword(encoder.encode(defaultPassword));
		return user;
	}

	@Override
	public UserInfo convert(final User user) {
		final UserInfo userInfo = new UserInfo();
		userInfo.setName(user.getName());
		userInfo.setRole(user.getRole().name());
		return userInfo;
	}
}
