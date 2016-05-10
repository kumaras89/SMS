package com.sms.core.admin;

import java.util.List;

public interface UserService {

    void save(final UserInfo user);
    
    void edit(final UserInfo user);
    
    void remove(final String userName);
    
    List<UserInfo> list();

    UserInfo findUserByName(final String userName);


}
