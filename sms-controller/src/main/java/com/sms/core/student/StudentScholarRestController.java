package com.sms.core.student;

import com.sms.core.scholarship.StudentScholarSearchCriteria;
import com.sms.core.scholarship.StudentScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/scholarshipenrollment")
public class StudentScholarRestController {

    private final StudentScholarService studentScholar;

    @Autowired
    public StudentScholarRestController(final StudentScholarService studentScholarEnrollment) {
        studentScholar = studentScholarEnrollment;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentScholarInfo>> listAll() {
        return Optional.ofNullable(studentScholar.findAll())
            .filter(e -> !e.isEmpty())
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final StudentScholarInfo studentScholarInfo) {

        studentScholar.save(studentScholarInfo);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{applicationNumber}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentScholarInfo> get(@PathVariable("applicationNumber") final String applicationNumber) {

        return studentScholar.
            findByApplicationNumber(applicationNumber).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<StudentScholarInfo>> search(
        @RequestBody @Valid final StudentScholarSearchCriteria criteria) {

        return Optional.ofNullable(studentScholar.search(criteria))
            .filter(e -> !e.isEmpty())
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{applicationNumber}", method = RequestMethod.PUT)
    public ResponseEntity<StudentScholarInfo> update(
        @PathVariable("applicationNumber") final String applicationNumber,
        @RequestBody @Valid final StudentScholarInfo studentScholarInfo) {

        return studentScholar.update(applicationNumber, studentScholarInfo)
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .get();
    }
}

