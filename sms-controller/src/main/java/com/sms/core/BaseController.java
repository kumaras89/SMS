package com.sms.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public abstract class BaseController<T> {

    private final IStudentPortalService<T> studentPortalService;

    protected BaseController(final IStudentPortalService<T> studentPortalService) {
        this.studentPortalService = studentPortalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> listAll() {
        return Optional.ofNullable(studentPortalService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable("id") final long id) {
        return studentPortalService.
                findById(id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final T entityObject, final UriComponentsBuilder ucBuilder) {

        final Optional<T> t = studentPortalService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable("id") final long id,
                                    @RequestBody @Valid final T entityObject) {
        return studentPortalService.update(id, entityObject)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("id") final long id) {
        studentPortalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
