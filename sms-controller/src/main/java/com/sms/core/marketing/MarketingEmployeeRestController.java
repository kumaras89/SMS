package com.sms.core.marketing;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketingEmployee")
public class MarketingEmployeeRestController extends BaseController<MarketingEmployeeInfo> {

    @Autowired
    public MarketingEmployeeRestController(final IStudentPortalService<MarketingEmployeeInfo> marketingEmployeeService) {
        super(marketingEmployeeService);
    }
}
