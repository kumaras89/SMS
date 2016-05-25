package com.sms.core.admin;

import com.sms.core.SmsSessionContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    private final UserCredentialService userCredentialService;
    private final SmsSessionContextService sessionContextService;

    @Autowired
    public LoginRestController(final UserCredentialService userCredentialService, SmsSessionContextService smsSessionContextService) {
        this.userCredentialService = userCredentialService;
        this.sessionContextService = smsSessionContextService;
    }



    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public void changePassword(@RequestBody @Valid Password password) {
        userCredentialService.changePassword(password);
    }


    @RequestMapping(value = "/securedoperation", method = RequestMethod.GET)
    public List<String> getAllSecuredOperations() {
        return sessionContextService.getSecuredOperations();
    }

}
