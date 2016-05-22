package com.sms.core.branch;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchRestController extends BaseController<Branch> {

    @Autowired
    public BranchRestController(final IStudentPortalService<Branch> branchService) {
        super(branchService);
    }
    
 }
