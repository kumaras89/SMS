package com.sms.core.message;

import com.sms.core.scholarship.StudentScholarFacade;
import com.sms.core.scholarship.StudentScholarService;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/12/2016.
 */
@RestController
@RequestMapping("/messageservice")
public class MessageRestController
{
    private final MessageService messageService;

    @Autowired
    public MessageRestController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> sendAllStudentScholar(@RequestBody @Valid final MessageInfo entityObject,
                                                      final UriComponentsBuilder ucBuilder)
    {
        messageService.sendMessageToAll(entityObject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MessageInfo>> listAll() {
        return Optional.ofNullable(messageService.listAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity(e, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));

    }

}
