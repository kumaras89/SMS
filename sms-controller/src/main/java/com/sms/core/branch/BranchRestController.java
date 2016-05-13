package com.sms.core.branch;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchRestController extends BaseController<Branch> {

    @Autowired
    public BranchRestController(final IStudentPortalService<Branch> branchService) {
        super(branchService);
    }
}
