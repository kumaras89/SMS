package com.sms.core.admin;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserRestController extends BaseController<UserInfo>{

    private UserCredentialService credentialService;
    @Autowired
    public UserRestController(final IStudentPortalService<UserInfo> userService, UserCredentialService credentialService) {
        super(userService);
        this.credentialService = credentialService;
    }

    @RequestMapping(value = "/resetpassword/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> resetPassword(@PathVariable("id") long id) {
        credentialService.resetPassword(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
