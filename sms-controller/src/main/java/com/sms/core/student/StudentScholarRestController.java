package com.sms.core.student;
import com.sms.core.IStudentScholarService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/scholarshipEnrollment")
public class StudentScholarRestController
{
    private final IStudentScholarService studentScholar;

    @Autowired
    public StudentScholarRestController(final IStudentScholarService studentScholarEnrollment)
    {
        studentScholar = studentScholarEnrollment;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentScholarInfo>> listAll()
    {
        return Optional.ofNullable(studentScholar.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity(e, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid StudentScholarInfo entityObject)
    {

        System.out.println("i am in student scholar --create");
        studentScholar.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentScholarInfo> get(@PathVariable("id") long id) {
        return studentScholar.
                findById(id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}

