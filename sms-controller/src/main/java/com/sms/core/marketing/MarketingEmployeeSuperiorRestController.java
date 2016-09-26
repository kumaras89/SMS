package com.sms.core.marketing;

import com.sms.core.common.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:40.
 *  
 */
@RestController
@RequestMapping("/marketingemployee")
public class MarketingEmployeeSuperiorRestController {

    @Autowired
    private IMarketingEmployeeSuperiorService superiorService;

    @RequestMapping(value = "/superiors/{designation}",method = RequestMethod.GET)
    public ResponseEntity<List<MarketingEmployee>> getAllSuperiors(@PathVariable final Designation designation) {

        return Optional.ofNullable(superiorService.getAllMarketingEmployee(designation))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
