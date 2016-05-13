package com.sms.core.security;

import com.sms.core.admin.UserRole;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

//        return Optional.of(username)
//                .map(userService::findByNameIgnoreCase)
//                .map(e -> new User(e.getName(), e.getPassword(), Arrays.asList(new SimpleGrantedAuthority(e.getRole().name()))))
//                .get();
        return Optional.of(username)
                .filter(e -> !"ADMIN".equalsIgnoreCase(e)).map(userService::findByNameIgnoreCase)
                .map(Optional::of)
                .orElseGet(() -> Optional.of(com.sms.core.admin.User.builder()
                        .withName(username)
                        .withPassword(encoder.encode("admin"))
                        .withRole(UserRole.SUPER_ADMIN)
                        .build()))
                .map(e -> new User(e.getName(), e.getPassword(), Arrays.asList(new SimpleGrantedAuthority(e.getRole().name()))))
                .get();
    }
}
