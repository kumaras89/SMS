package com.sms.core.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sms.core.admin.UserInfo;
import com.sms.core.admin.UserRole;
import com.sms.core.admin.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Supplier<Optional<UserInfo>> userInfoSupplier = () -> {
			UserInfo ui = new UserInfo();
			ui.setName(username);
			ui.setPassword("admin");
			ui.setRole(UserRole.SUPER_ADMIN.name());
			return Optional.of(ui);
		};
		Function<String, List<? extends GrantedAuthority>> getAuthority = (e) -> Optional.of(e)
				.map(SimpleGrantedAuthority::new).map(Arrays::asList).get();
		//Optional<User> user = Optional.of(username).filter(e -> !"ADMIN".equalsIgnoreCase(e)).map(userService::findUserByName)
		//		.map(Optional::of).orElseGet(userInfoSupplier)
		//		.map(e -> { System.out.println(e);return new User(e.getName(), encoder.encode(e.getPassword()), getAuthority.apply(e.getRole()));})
				;
		//System.out.println(user);
		return new User(username, encoder.encode("admin"), getAuthority.apply("ROLE_USER"));
	}
}
