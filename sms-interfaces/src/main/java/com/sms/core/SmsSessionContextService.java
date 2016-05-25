package com.sms.core;

import com.sms.core.admin.SecuredOperation;
import com.sms.core.admin.SecuredOperationType;
import com.sms.core.admin.User;
import com.sms.core.admin.UserInfo;
import com.sms.core.repositery.RoleOperationLinkRepository;
import com.sms.core.repositery.SecuredOperationRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ganesan on 25/05/16.
 */
@Service
public class SmsSessionContextService {

    @Autowired
    private SmsSessionContext sessionContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleOperationLinkRepository roleOperationLinkRepository;

    @Autowired
    private SecuredOperationRepository securedOperationRepository;

    public SmsSessionContext initSessionContext(String userName) {
        User user = userRepository.findByNameIgnoreCase(userName);
        sessionContext.setContextIntialized(true);
        sessionContext.setLoggedInUserInfo(UserInfo.toBuilder(user).build());
        sessionContext.setSecuredOperations(getSecuredOperations());
        sessionContext.setAllowedOperations(getSecuredOperationByType(user.getRole().getName(), OPERATION));
        sessionContext.setAllowedUrls(getSecuredOperationByType(user.getRole().getName(), URL));
        return sessionContext;
    }

    private List<String> getSecuredOperationByType(String userRole, Predicate<SecuredOperation> filter) {
        return roleOperationLinkRepository.findAll().stream().filter(rol -> rol.getUserRole().getName().equals(userRole)).map(s-> s.getSecuredOperation()).filter(filter).map(s -> s.getOperation()).collect(Collectors.toList());
    }

    public List<String> getSecuredOperations() {
        return getSecuredByType(OPERATION);
    }


    private List<String> getSecuredByType(Predicate<SecuredOperation> filter) {
        return securedOperationRepository.findAll().stream().filter(filter).map(s -> s.getOperation()).collect(Collectors.toList());
    }

    public List<String> getSecuredUrls() {
        return getSecuredByType(URL);
    }


    private static Predicate<SecuredOperation> URL = so -> SecuredOperationType.URL.equals(so.getType());
    private static Predicate<SecuredOperation> OPERATION = so -> SecuredOperationType.OPERATION.equals(so.getType());

}
