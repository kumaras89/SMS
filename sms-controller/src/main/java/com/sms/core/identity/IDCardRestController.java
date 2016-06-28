package com.sms.core.identity;

import com.sms.core.repositery.IdCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/idcard")
public class IDCardRestController {

    private final IdCardRepository idCardRepository;
    private final IDCardService idCardService;

    @Autowired
    public IDCardRestController(final IdCardRepository idCardRepository, final IDCardService idCardService) {
        this.idCardRepository = idCardRepository;
        this.idCardService = idCardService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<IdCardInfo>> listAll(@RequestBody @Valid IdCardSearchCriteria idCardInfo) {

        return Optional.ofNullable(idCardService.search(Optional.ofNullable(idCardInfo)).with(idCardRepository))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}