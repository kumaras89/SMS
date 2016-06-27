package com.sms.core.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ganesan on 27/06/16.
 */
@Controller
@RequestMapping("/")
public class InitController {

    @RequestMapping(method = RequestMethod.GET)
    public String redirectToIndex() {
        return "redirect:/public/index.html";
    }
}
