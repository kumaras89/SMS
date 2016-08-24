package com.sms.core.attendance;

import com.sms.core.scholarship.StudentScholarSearchCriteria;
import com.sms.core.student.StudentScholarInfo;
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
 * Created by sathish on 7/29/2016.
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceRestController {

    private AttendanceService attendanceService;

    @Autowired
    public AttendanceRestController(final AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final StudentAttendanceInfo entityObject,
                                       final UriComponentsBuilder ucBuilder) {
        attendanceService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentAttendanceInfo>> listAll() {
        return Optional.ofNullable(attendanceService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentAttendanceInfo> get(@PathVariable("id") final Long id) {
        return attendanceService.findById(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentAttendanceInfo> update(@PathVariable("id") final long id,
                                                   @RequestBody @Valid final StudentAttendanceInfo hotelTrackerInfo) {
        return attendanceService.update(id, hotelTrackerInfo)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ResponseEntity<List<AttendanceView>> search(@RequestBody @Valid AttendanceSearchCriteria criteria) {
        return Optional.ofNullable(attendanceService.search(criteria))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
