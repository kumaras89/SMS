package com.sms.core;

import com.sms.core.admin.*;
import com.sms.core.repositery.RoleOperationLinkRepository;
import com.sms.core.repositery.SecuredOperationRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
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
        List<SecuredOperation> securedOperations = securedOperationRepository.findAll();
        sessionContext.setSecuredOperations(getSecuredByType(OPERATION).apply(securedOperations));
        List<SecuredOperation> allowedOperationsForUser = findListOfSecuredOperation(getSecuredOperationByUser(user.getRole().getId()), securedOperations);
        sessionContext.setAllowedOperations(getSecuredByType(OPERATION).apply(allowedOperationsForUser));
        sessionContext.setAllowedUrls(getSecuredByType(URL).apply(allowedOperationsForUser));
        return sessionContext;
    }

    private List<SecuredOperation> findListOfSecuredOperation(List<RoleOperationLink> rols, List<SecuredOperation> securedOperations){
        return rols.stream()
                .map(rol -> securedOperations
                        .stream()
                        .filter(so -> so.getId().equals(rol.getSecuredOperationId()))
                        .findFirst()
                        .get())
                .collect(Collectors.toList());
    }

    private List<RoleOperationLink> getSecuredOperationByUser(Long userId) {
        return roleOperationLinkRepository.findAll().stream().filter(rol -> rol.getUserRoleId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<String> getSecuredOperations() {
        return getSecuredByType(OPERATION).apply(securedOperationRepository.findAll());
    }


    private Function<List<SecuredOperation>, List<String>> getSecuredByType(Predicate<SecuredOperation> filter) {
        return l -> l.stream().filter(filter).map(s -> s.getOperation()).collect(Collectors.toList());
    }

    public List<String> getSecuredUrls() {
        return getSecuredByType(URL).apply(securedOperationRepository.findAll());
    }


    private static Predicate<SecuredOperation> URL = so -> SecuredOperationType.URL.equals(so.getType());
    private static Predicate<SecuredOperation> OPERATION = so -> SecuredOperationType.OPERATION.equals(so.getType());

}
