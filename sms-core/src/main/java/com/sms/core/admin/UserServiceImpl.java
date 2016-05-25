package com.sms.core.admin;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.UserRepository;
import com.sms.core.repositery.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl extends BaseServiceConvertorImpl<UserInfo, User> {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(final UserRepository jpaRepository,
                           UserRoleRepository userRoleRepository,
                           final Converter<UserInfo, User> userConverter) {
        super(jpaRepository, (userInfo) -> User.toBuilder(userConverter.convert(userInfo))
                .on(u -> u.getRole()).set(userRoleRepository.findByName(userInfo.getRole())).build(), (source) -> UserInfo.toBuilder(source).build());
        this.userRepository = jpaRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserInfo> update(Long id, UserInfo entityType) {
        return  Optional.of(entityType)
                .map(e -> buildToPersistObject(id, entityType))
                .map(user -> User.toBuilder(user)
                        .on(u -> u.getPassword()).set(userRepository.findOne(id).getPassword()).build())
                .map(userRepository::saveAndFlush)
                .map(e -> UserInfo.toBuilder(e).build());
    }

    @Override
    protected User buildToPersistObject(Long id, UserInfo user) {
        return User.builder()
                .on(u-> u.getId()).set(id)
                .on(u-> u.getFirstName()).set(user.getFirstName())
                .on(u-> u.getLastName()).set(user.getLastName())
                .on(u-> u.getBranch()).set(user.getBranch())
                .on(u-> u.getRole()).set(userRoleRepository.findByName(user.getRole()))
                .on(u-> u.getName()).set(user.getName())
                .build();
    }
}
