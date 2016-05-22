package com.sms.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    private final UserCredentialService userCredentialService;

    @Autowired
    public LoginRestController(final UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public void changePassword(@RequestBody @Valid Password password) {
        userCredentialService.changePassword(password);
    }

}
