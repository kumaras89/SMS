package com.sms.core.admin;

import com.sms.core.repositery.RoleOperationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleOperationLinkServiceImpl implements RoleOperationLinkService {

    @Autowired
    private RoleOperationLinkRepository repository;

    @Override
    public List<RoleOperationLinkInfo> getAll() {
        return repository.findAll()
            .stream()
            .collect(Collectors
                .groupingBy(rol -> rol.getUserRoleId(), Collectors
                    .mapping(rol -> rol.getSecuredOperationId(), Collectors.toList())))
            .entrySet()
            .stream()
            .map(e -> new RoleOperationLinkInfo(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }

    @Override
    public void save(final RoleOperationLinkInfo roleOperationLinkInfo) {

        final List<RoleOperationLink> roleOperationLinks = repository.findAll().stream()
            .filter(u -> roleOperationLinkInfo.getUserRoleId().equals(u.getUserRoleId()))
            .collect(Collectors.toList());

        repository.delete(roleOperationLinks
            .stream()
            .filter(rol -> !roleOperationLinkInfo.getLinkedOperations().contains(rol.getSecuredOperationId()))
            .collect(Collectors.toList()));

        repository.save(roleOperationLinkInfo
            .getLinkedOperations()
            .stream()
            .filter(id -> !roleOperationLinks.stream().anyMatch(rol -> rol.getSecuredOperationId().equals(id)))
            .map(id -> new RoleOperationLink(roleOperationLinkInfo.getUserRoleId(), id))
            .collect(Collectors.toList()));
    }
}
