package com.sms.core.expense;

import com.sms.core.attendance.AttendanceSearchCriteria;
import com.sms.core.attendance.AttendanceService;
import com.sms.core.attendance.StudentAttendanceInfo;
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
 * Created by sathish on 8/16/2016.
 */

@RestController
@RequestMapping("/expense")
public class ExpenseRestController
{
    private ExpenseService expenseService;

    @Autowired
    public ExpenseRestController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final ExpenseInfo entityObject,
                                       final UriComponentsBuilder ucBuilder) {
        expenseService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseInfo>> listAll() {
        return Optional.ofNullable(expenseService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseInfo> get(@PathVariable("id") final Long id) {
        return expenseService.findById(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ExpenseInfo> update(@PathVariable("id") final long id,
                                                        @RequestBody @Valid final ExpenseInfo expenseInfo) {
        return expenseService.update(id, expenseInfo)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ResponseEntity<List<ExpenseInfo>> search(@RequestBody @Valid ExpenseSearchCriteria criteria) {
        return Optional.ofNullable(expenseService.search(criteria))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
