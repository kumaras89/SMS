package com.sms.core;

import com.sms.core.admin.*;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.RoleOperationLinkRepository;
import com.sms.core.repositery.SecuredOperationRepository;
import com.sms.core.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 25/05/16.
 * <p></p>
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
    private MarketingEmployeeRepository marketingEmployeeRepository;

    @Autowired
    private SecuredOperationRepository securedOperationRepository;

    public SmsSessionContext initSessionContext(final String userName) {
        final User user = userRepository.findByNameIgnoreCase(userName);
        sessionContext.setContextIntialized(true);
        sessionContext.setLoggedInUserInfo(UserInfo.toBuilder(user).build());
        final List<SecuredOperation> securedOperations = securedOperationRepository.findAll();
        sessionContext.setSecuredOperations(getSecuredByType(OPERATION).apply(securedOperations));
        final List<SecuredOperation> allowedOperationsForUser =
            findListOfSecuredOperation(getSecuredOperationByUser(user.getRole().getId()), securedOperations);
        sessionContext.setAllowedOperations(getSecuredByType(OPERATION).apply(allowedOperationsForUser));
        sessionContext.setAllowedUrls(getSecuredByType(URL).apply(allowedOperationsForUser));
        sessionContext.setMarkettingEmployeeCode(
            Optional.ofNullable(marketingEmployeeRepository.findByUserName(userName))
                .map(MarketingEmployee::getCode)
                .orElse(null));

        return sessionContext;
    }

    private List<SecuredOperation> findListOfSecuredOperation(
        final List<RoleOperationLink> rols,
        final List<SecuredOperation> securedOperations) {

        return rols.stream()
            .map(rol -> securedOperations
                .stream()
                .filter(so -> so.getId().equals(rol.getSecuredOperationId()))
                .findFirst()
                .get())
            .collect(Collectors.toList());
    }

    private List<RoleOperationLink> getSecuredOperationByUser(final Long userId) {
        return roleOperationLinkRepository
            .findAll()
            .stream()
            .filter(rol -> rol.getUserRoleId().equals(userId))
            .collect(Collectors.toList());
    }

    public List<String> getSecuredOperations() {
        return getSecuredByType(OPERATION).apply(securedOperationRepository.findAll());
    }

    private Function<List<SecuredOperation>, List<String>> getSecuredByType(final Predicate<SecuredOperation> filter) {

        return l -> l.stream()
            .filter(filter)
            .map(s -> s.getOperation())
            .collect(Collectors.toList());
    }

    public List<String> getSecuredUrls() {
        return getSecuredByType(URL).apply(securedOperationRepository.findAll());
    }

    private static final Predicate<SecuredOperation> URL = so -> SecuredOperationType.URL == so.getType();
    private static final Predicate<SecuredOperation> OPERATION = so -> SecuredOperationType.OPERATION == so.getType();
}
