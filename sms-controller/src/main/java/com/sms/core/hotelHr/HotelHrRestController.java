package com.sms.core.hotelHr;

import com.sms.core.hotel.HotelInfo;
import com.sms.core.hotel.HotelService;
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
 * Created by sathish on 7/18/2016.
 */
@RestController
@RequestMapping("/hotelhr")
public class HotelHrRestController
{
    @Autowired
    private HotelHrService hotelHrService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<HotelInfo>> listAll() {
        return Optional.ofNullable(hotelHrService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity(e, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final HotelHrInfo entityObject,
                                       final UriComponentsBuilder ucBuilder) {
        hotelHrService.save(entityObject);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelHrInfo> get(@PathVariable("id")
                                         final long id) {
        return hotelHrService.
                findById(id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HotelHrInfo> update(@PathVariable("id") long id,
                                            @RequestBody @Valid HotelHrInfo entityObject) {
        return hotelHrService.update(id, entityObject)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

}