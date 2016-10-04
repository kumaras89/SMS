package com.sms.core.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 8/16/2016.
 * <p></p>
 */
@RestController
@RequestMapping("/expense")
public class ExpenseRestController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseDetailsService expenseDetailsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final ExpenseInfo entityObject) {
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
    public ResponseEntity<ExpenseInfo> update(
            @PathVariable("id") final long id,
            @RequestBody @Valid final ExpenseInfo expenseInfo) {
        return expenseService.update(id, expenseInfo)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<ExpenseInfo>> search(@RequestBody @Valid final ExpenseSearchCriteria criteria) {
        return Optional.ofNullable(expenseService.search(criteria))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/totalExpense", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> totalExpense(@RequestBody @Valid final ExpenseSearchCriteria criteria) {
        return new ResponseEntity<>(expenseService.totalExpense(criteria), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        expenseService.deleteExpense(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "expenseDetails/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDetails(@PathVariable("id") final Long id) {
        expenseDetailsService.deleteExpenseDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
