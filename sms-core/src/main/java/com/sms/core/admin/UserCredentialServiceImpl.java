package com.sms.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.core.SmsException;
import com.sms.core.repositery.UserRepositery;

@Service("userCredentialService")
@Transactional
public class UserCredentialServiceImpl implements UserCredentialService {

    @Autowired
    private UserRepositery userRepositery;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${PASSWORD_NOT_MATCH_ERROR_MSG}")
    private String passwordNotMatchErrorMsg;

    public void changePassword(final Password password) {

        final User user = userRepositery.findByNameIgnoreCase(password.getUserName());
        if (isPasswordMatches(password.getOldPassword(), user.getPassword())) {
            userRepositery.changePassword(encoder.encode(password.getNewPassword()), user.getName());
        } else {
            throw new SmsException(passwordNotMatchErrorMsg);
        }
    }

    private boolean isPasswordMatches(final String oldPassword, final String existingPassword) {
        return encoder.encode(oldPassword).equals(existingPassword);
    }
}
