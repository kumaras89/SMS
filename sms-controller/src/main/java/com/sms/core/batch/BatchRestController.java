package com.sms.core.batch;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchRestController extends BaseController<BatchInfo> {

    @Autowired
    public BatchRestController(final IStudentPortalService<BatchInfo> batchService) {
        super(batchService);
    }
}
