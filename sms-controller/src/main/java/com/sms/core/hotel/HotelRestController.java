package com.sms.core.hotel;

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
 * Created by sathish on 7/15/2016.
 * <p></p>
 */
@RestController
@RequestMapping("/hotel")
public class HotelRestController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<HotelInfo>> listAll() {
        return Optional.ofNullable(hotelService.findAll())
                       .filter(e -> !e.isEmpty())
                       .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final HotelInfo hotelInfo ) {
        hotelService.save(hotelInfo);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelInfo> get(@PathVariable("id")final long hotelId) {
        return hotelService.
                            findById(hotelId)
                            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HotelInfo> update(@PathVariable("id") final long id,
                                            @RequestBody @Valid final HotelInfo hotelInfo) {
        return hotelService.update(id, hotelInfo)
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .get();
    }

}
