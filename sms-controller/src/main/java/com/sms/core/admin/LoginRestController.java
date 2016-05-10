package com.sms.core.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sms.core.admin.Password;
import com.sms.core.admin.UserCredentialService;

@RestController
@RequestMapping("/login")
public class LoginRestController {
	
	@Autowired
	private UserCredentialService userCredentialService;
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public void changePassword(@RequestBody @Valid Password password) {
		userCredentialService.changePassword(password);
	}
}
