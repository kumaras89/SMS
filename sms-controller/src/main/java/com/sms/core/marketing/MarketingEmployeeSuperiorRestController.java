package com.sms.core.marketing;

import com.sms.core.common.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:40.
 *  
 */
@RestController
@RequestMapping("/marketingemployee")
public class MarketingEmployeeSuperiorRestController {

    @Autowired
    private IMarketingEmployeeSuperiorService superiorService;

    @RequestMapping("/superiors/{designation}")
    public List<MarketingEmployee> getAllSuperiors(@PathVariable final Designation designation) {

        return superiorService.getAllMarketingEmployee(designation);
    }
}
