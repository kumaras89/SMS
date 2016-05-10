package com.sms.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public abstract class BaseController<T> {

    private final IStudentPortalService<T> studentPortalService;

    public BaseController(final IStudentPortalService<T> studentPortalService) {
        this.studentPortalService = studentPortalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> listAll() {
        final List<T> entityObjects = studentPortalService.findAll();
        if (entityObjects.isEmpty()) {
            return new ResponseEntity<List<T>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<T>>(entityObjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable("id") long id) {

        System.out.println("Fetching with id " + id);
        final Optional<T> entityObject = studentPortalService.findById(id);

        if (!entityObject.isPresent()) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<T>(entityObject.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody T entityObject, UriComponentsBuilder ucBuilder) {

        studentPortalService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable("id") long id,
                                    @RequestBody T entityObject) {
        final Optional<T> persistedStudent = studentPortalService.update(id, entityObject);
        return new ResponseEntity<T>(persistedStudent.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("id") long id) {

        System.out.println("Fetching & Deleting Student with id " + id);
        studentPortalService.delete(id);
        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }
}
