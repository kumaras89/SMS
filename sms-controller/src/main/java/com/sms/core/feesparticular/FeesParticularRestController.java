package com.sms.core.feesparticular;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feesParticular")
public class FeesParticularRestController extends BaseController<FeesParticular> {

    @Autowired
    public FeesParticularRestController(final IStudentPortalService<FeesParticular> feesParticularServiceImpl) {
        super(feesParticularServiceImpl);
    }
}


