package com.sms.core.admin;

import com.sms.core.repositery.UserRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositery userRepositery;

	@Autowired
	private UserConverter userConverter;

	@Override
	public void save(final UserInfo userInfo) {
		final User user = userConverter.convert(userInfo);
		userRepositery.saveAndFlush(user);
	}

	@Override
	public void edit(final UserInfo userInfo) {
		final User user = userConverter.convert(userInfo);
		userRepositery.edit(user.getRole(), user.getName());
	}

	@Override
	public List<UserInfo> list() {
		return userRepositery.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
	}

	@Override
	public UserInfo findUserByName(String userName) {
		return Optional.of(userName).map(userRepositery::findByNameIgnoreCase).map(userConverter::convert)
				.orElseGet(UserInfo::new);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void remove(final String userName) {
		userRepositery.remove(userName);
	}
}
