package com.sms.core.admin;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import com.sms.core.repositery.RoleOperationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ganesan on 25/05/16.
 */
@RestController
@RequestMapping(value = "/roleoperationlink")
public class RoleOperationLinkRestController {

    private RoleOperationLinkService roleOperationLinkService;

    @Autowired
    public RoleOperationLinkRestController(final RoleOperationLinkService roleOperationLinkService) {
        this.roleOperationLinkService = roleOperationLinkService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoleOperationLinkInfo>> listAll() {
        return Optional.ofNullable(roleOperationLinkService.getAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid RoleOperationLinkInfo entityObject) {
        roleOperationLinkService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


}
