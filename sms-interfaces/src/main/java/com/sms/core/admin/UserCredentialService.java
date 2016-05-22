package com.sms.core.admin;

public interface UserCredentialService {
	
	void changePassword(final Password password);

	void resetPassword(final Long userName);
}
