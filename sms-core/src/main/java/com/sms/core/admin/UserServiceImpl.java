package com.sms.core.admin;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl extends BaseServiceConvertorImpl<UserInfo, User> {

    @Autowired
    public UserServiceImpl(final UserRepository jpaRepository,
                           final Converter<UserInfo, User> userConverter) {
        super(jpaRepository, userConverter, (source) -> UserInfo.toBuilder(source).build());
    }

    @Override
    protected User buildToPersistObject(Long id, UserInfo user) {
        return User.builder()
                .withId(id)
                .withPassword(user.getPassword())
                .withRole(UserRole.valueOf(user.getRole()))
                .withName(user.getName())
                .build();
    }
}
