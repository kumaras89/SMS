package com.sms.core.admin;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ganesan on 25/05/16.
 */
@RestController
@RequestMapping(value = "/securedoperation")
public class SecuredOperationRestController extends BaseController<SecuredOperation> {

    @Autowired
    public SecuredOperationRestController(final IStudentPortalService<SecuredOperation> securedOperationService) {
        super(securedOperationService);
    }

}
