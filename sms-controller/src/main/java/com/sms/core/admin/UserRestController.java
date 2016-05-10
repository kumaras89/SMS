package com.sms.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody final UserInfo user) {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserInfo> listUsers() {
        return userService.list();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void edit(@RequestBody final UserInfo user) {
        userService.edit(user);
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
    public void remove(@PathVariable final String userName) {
        userService.remove(userName);
    }
}
