package com.sms.core.scheme;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feesCategory")
public class FeesCategoryRestController extends BaseController<FeesCategoryInfo> {

    @Autowired
    public FeesCategoryRestController(final IStudentPortalService<FeesCategoryInfo> feesCategoryServiceImpl) {
        super(feesCategoryServiceImpl);
    }
}


