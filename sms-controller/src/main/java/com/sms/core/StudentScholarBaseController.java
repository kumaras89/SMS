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

/**
 * Created by sathish on 6/20/2016.
 */
@RestController
public abstract class StudentScholarBaseController<T>
{
    private final IStudentScholarService<T> studentScholar;

    public StudentScholarBaseController(final IStudentScholarService<T> studentScholar) {
        this.studentScholar = studentScholar;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> listAll() {

        System.out.print("hello world------dfhdhdfhfdhfhhd--------------------");
        return Optional.ofNullable(studentScholar.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable("id") long id) {
        return studentScholar.
                findById(id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid T entityObject, UriComponentsBuilder ucBuilder)
    {
        System.out.print("hello world--------------------------");
        studentScholar.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
