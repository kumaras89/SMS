package com.sms.core.message;

import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by sathish on 7/12/2016.
 * <p></>
 */
@RestController
@RequestMapping("/messageservice")
public class MessageRestController {

    @Autowired
    private MessageFacade messageFacade;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<String>> sendAllStudentScholar(@RequestBody @Valid final MessageInfo messageInfo) {
        return new ResponseEntity<>(messageFacade.sendMessage(messageInfo),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MessageInfo>> listAll() {
        return Optional.ofNullable(messageFacade.listAll())
                       .filter(messageInfos -> !messageInfos.isEmpty())
                       .map(messageInfos -> new ResponseEntity<>(messageInfos,HttpStatus.ACCEPTED))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
