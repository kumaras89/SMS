package com.sms.core.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sms.core.admin.User;
import com.sms.core.admin.UserRole;

public interface UserRepository extends JpaRepository<User,Long> {
	
	@Modifying
	@Query("update User u set u.role = ? where u.name = ?")
	void edit(final UserRole role, final String name);
	
	@Modifying
	@Query("delete from User u where u.name = ?")
	void remove(final String name);
	
	@Modifying
	@Query("update User u set u.password = ? where u.name = ?")
	void changePassword(final String password, final String name);

	User findByNameIgnoreCase(final String name);
}
