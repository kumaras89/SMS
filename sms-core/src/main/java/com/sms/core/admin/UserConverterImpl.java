package com.sms.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements Converter<UserInfo, User> {

    @Autowired
    private PasswordEncoder encoder;

    @Value("${DEFAULT_PASSWORD}")
    private String defaultPassword;

    @Override
    public User convert(final UserInfo userInfo) {
        return User.builder()
                .withName(userInfo.getName())
                .withRole(UserRole.valueOf(userInfo.getRole().toUpperCase()))
                .withPassword(encoder.encode(defaultPassword))
                .build();
    }
}
