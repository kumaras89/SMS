package com.sms.core.marketing;

import com.sms.core.repositery.MarketingCommissionSplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 9/26/2016.
 */
@RestController
@RequestMapping("/marketingsplit")
public class MarketingSplitConfigRestController {

    private final MarketingSplitConfigService configService;

    @Autowired
    public MarketingSplitConfigRestController(final MarketingSplitConfigService marketingSplitConfigService) {
        configService = marketingSplitConfigService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MarketingCommissionConfigSplitInfo>> listAll() {
        return Optional.ofNullable(configService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final MarketingCommissionConfigSplitInfo commissionConfigSplitInfo) {

        configService.save(commissionConfigSplitInfo);
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MarketingCommissionConfigSplitInfo> update(
            @PathVariable("id") final long id,
            @RequestBody @Valid final MarketingCommissionConfigSplitInfo commissionConfigSplitInfo) {
        return configService.update(id, commissionConfigSplitInfo)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MarketingCommissionConfigSplitInfo> findById(
            @PathVariable("id") final long id) {
        return configService.find(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    //fetching all inforamtion about commission details
    @RequestMapping(value = "/commissonsplitdetails", method = RequestMethod.GET)
    public ResponseEntity<List<MarketingCommissionSplitInfo>> getAll() {
        return Optional.ofNullable(configService.getAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
