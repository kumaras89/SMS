package com.sms.core.admin;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class UserRestController extends BaseController<UserInfo>{

    @Autowired
    public UserRestController(final IStudentPortalService<UserInfo> userService) {
        super(userService);
    }
}
