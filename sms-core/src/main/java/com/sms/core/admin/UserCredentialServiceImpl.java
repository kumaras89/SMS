package com.sms.core.admin;

import com.sms.core.SmsException;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Value("${DEFAULT_PASSWORD}")
    private String defaultPassword;

    /**
     * @param theId
     */
    @Override
    public void resetPassword(final Long theId) {
        final User user = userRepository.findOne(theId);
        userRepository.changePassword(encoder.encode(defaultPassword), user.getName());
    }

    /**
     * @param password
     */
    @Override
    public void changePassword(final Password password) {
        Optional.ofNullable(password)
            .filter(pass -> pass.getNewPassword().equals(pass.getReTypeNewPwd()))
            .map(Password::getUserName)
            .orElseThrow(() ->
                new SmsException("reTypeNewPwd", "New Password and Retype New Password not matches"));

        final User user = userRepository.findByNameIgnoreCase(password.getUserName());

        if (user == null) {
            throw new SmsException("userName", "user name not found");
        }
        if (isPasswordMatches(password.getOldPassword(), user.getPassword())) {
            userRepository.changePassword(encoder.encode(password.getNewPassword()), user.getName());
        } else {
            throw new SmsException("password", passwordNotMatchErrorMsg);
        }
    }

    /**
     * @param oldPassword
     * @param existingPassword
     * @return
     */
    private boolean isPasswordMatches(final String oldPassword, final String existingPassword) {
        return encoder.matches(oldPassword, existingPassword);
    }
}
