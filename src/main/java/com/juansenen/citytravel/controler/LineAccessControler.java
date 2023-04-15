package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.dto.inAccessDTO;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.ErrorResponse;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.service.LineAccessService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineAccessControler {

    @Autowired
    LineAccessService lineAccessService;

    private final Logger logger = LoggerFactory.getLogger(LineAccessControler.class);

    @GetMapping("/accesses")
    public ResponseEntity<List<outAccessDTO>> getAll(@RequestParam(name = "elevator",defaultValue = "",required = false) String elev){
        logger.info("Begin get Access with or without @RequestParam");
        if (elev.equals("")){
            logger.info("Finish get Access");
            return ResponseEntity.ok(lineAccessService.findAll());
        }
        boolean elevator = Boolean.parseBoolean(elev);
        logger.info("Finish get Access WITH OR WITHOUT @RequestParam");
        return ResponseEntity.ok(lineAccessService.searchAccessWithElevator(elevator));
    }

    @GetMapping("/access/{id}")
    public ResponseEntity<Optional<LineAccess>> getById(@PathVariable long id){
        logger.info("Begin get access by Id");
        Optional<LineAccess> accessId = lineAccessService.findById(id);
        logger.info("Finish get access by Id");
        return new ResponseEntity<>(accessId, HttpStatus.OK);
    }

    @PostMapping("/access/{stationid}/access")
    public ResponseEntity<LineAccess> addOneAccess(@PathVariable long stationid, @RequestBody LineAccess lineAccess) throws NotFoundException {
        logger.info("Begin add access by station Id");
        LineAccess newOneAccess = lineAccessService.addNewAccessByStation(lineAccess, stationid);
        logger.info("Finish add access by station Id");
        return ResponseEntity.status(HttpStatus.CREATED).body(newOneAccess);
    }
    @PatchMapping("/access/{accessId}/access")
    public ResponseEntity<LineAccess> updateAccess(@PathVariable long accessId, @RequestBody LineAccess lineAccess) throws NotFoundException {
        logger.info("Begin update partialy access by access Id");
        LineAccess updateAccess = lineAccessService.updateOneAccess(accessId, lineAccess);
        logger.info("End update partialy access by access Id");
        return ResponseEntity.status(HttpStatus.CREATED).body(updateAccess);
    }

    @PutMapping("/access/{id}")
    public ResponseEntity<LineAccess> modyAccess(@PathVariable long id, @RequestBody LineAccess lineAccess) throws NotFoundException {
        logger.info("Begin modify access by Id");
        LineAccess changeAccess = lineAccessService.modyAccess(id, lineAccess);
        logger.info("Finish modify access by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeAccess);
    }

    @DeleteMapping("/access/{id}")
    public ResponseEntity<Void> delOneAccess(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete access by Id");
        try {
            lineAccessService.delAccess(id);
            logger.info("Finish delete access by Id");
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
