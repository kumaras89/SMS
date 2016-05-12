package com.sms.core.admin;

import com.sms.core.SmsException;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userCredentialService")
@Transactional
public class UserCredentialServiceImpl implements UserCredentialService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserCredentialServiceImpl(final UserRepository userRepository, final PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Value("${PASSWORD_NOT_MATCH_ERROR_MSG}")
    private String passwordNotMatchErrorMsg;

    public void changePassword(final Password password) {

        final User user = userRepository.findByNameIgnoreCase(password.getUserName());
        if (isPasswordMatches(password.getOldPassword(), user.getPassword())) {
            userRepository.changePassword(encoder.encode(password.getNewPassword()), user.getName());
        } else {
            throw new SmsException(passwordNotMatchErrorMsg);
        }
    }

    private boolean isPasswordMatches(final String oldPassword, final String existingPassword) {
        return encoder.encode(oldPassword).equals(existingPassword);
    }
}
