package com.sms.core.hotelTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 7/19/2016.
 * <p></p>
 */
@RestController
@RequestMapping("/hoteltracker")
public class HotelTrackerRestController {

    @Autowired
    private HotelTrackerService hotelTrackerService;

    @Autowired
    private HotelTrackerFacade hotelTrackerFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<HotelTrackerInfo>> listAll() {
        return Optional.ofNullable(hotelTrackerService.findAll())
                       .filter(e -> !e.isEmpty())
                       .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelTrackerInfo> get(@PathVariable("id") final Long id) {
        return hotelTrackerService.findById(id)
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final HotelTrackerInfo hotelTrackerInfo) {
        hotelTrackerService.save(hotelTrackerInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<HotelTrackerInfo>> search(@RequestBody @Valid
                                                          final HotelTrackerSearchCriteria hotelTrackerSearchCriteria) {
        return Optional.ofNullable(hotelTrackerFacade.search(hotelTrackerSearchCriteria))
                       .filter(e -> !e.isEmpty())
                       .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
