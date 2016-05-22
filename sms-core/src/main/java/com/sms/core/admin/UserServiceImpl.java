package com.sms.core.admin;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl extends BaseServiceConvertorImpl<UserInfo, User> {

    private UserRepository userRepository;
    private Converter<UserInfo, User> userConverter;

    @Autowired
    public UserServiceImpl(final UserRepository jpaRepository,
                           final Converter<UserInfo, User> userConverter) {
        super(jpaRepository, userConverter, (source) -> UserInfo.toBuilder(source).build());
        this.userRepository = jpaRepository;
        this.userConverter = userConverter;
    }

    @Override
    public Optional<UserInfo> update(Long id, UserInfo entityType) {
        return  Optional.of(entityType)
                .map(e -> userConverter.convert(e))
                .map(User::toBuilder)
                .map(e-> e.withPassword(userRepository.findOne(id).getPassword()))
                .map(User.Builder::build)
                .map(userRepository::saveAndFlush)
                .map(e -> UserInfo.toBuilder(e).build());
    }

    @Override
    protected User buildToPersistObject(Long id, UserInfo user) {
        return User.builder()
                .withId(id)
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withBranch(user.getBranch())
                .withRole(UserRole.valueOf(user.getRole()))
                .withName(user.getName())
                .build();
    }
}
