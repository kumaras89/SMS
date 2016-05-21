package com.sms.core.scheme;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheme")
public class SchemeRestController extends BaseController<SchemeInfo> {

    @Autowired
    public SchemeRestController(final IStudentPortalService<SchemeInfo> schemeServiceImpl) {
        super(schemeServiceImpl);
    }
}