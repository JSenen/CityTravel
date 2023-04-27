package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inStationDTO;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import com.juansenen.citytravel.exception.*;
import com.juansenen.citytravel.service.LineStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.executable.ValidateOnExecution;
import java.util.List;
import java.util.Optional;

@RestController
public class LineStationControler {

    @Autowired
    LineStationService lineStationService;

    private final Logger logger = LoggerFactory.getLogger(LineStation.class);

    @GetMapping("/stations")
    public ResponseEntity<List<LineStation>> getAll(@RequestParam(name="wifi",defaultValue = "",required = false) String wifi,
                                                      @RequestParam(name="busStation", defaultValue = "",required = false) String busStation,
                                                      @RequestParam(name="taxiStation", defaultValue = "", required = false) String taxiStation,
                                                    @RequestParam(name="pto_info", defaultValue = "", required = false) String ptoInfo){
        logger.info("Begin get station with or without @RequestParam");
        try{
            if (wifi.equals("") && busStation.equals("") && taxiStation.equals("") && ptoInfo.equals("")){
                logger.info("Finish get station without @RequestParam");
                return ResponseEntity.ok(lineStationService.findAll());
            }
            boolean hasBus = Boolean.parseBoolean(busStation);
            boolean haswifi = Boolean.parseBoolean(wifi);
            boolean hasTaxi = Boolean.parseBoolean(taxiStation);
            boolean hasPtoInfo = Boolean.parseBoolean(ptoInfo);
            logger.info("Finish get station with @RequestParam");
            return ResponseEntity.ok(lineStationService.findAllStationWithWifiBusAndTaxi(haswifi, hasBus, hasTaxi,hasPtoInfo));
        }catch (Exception excp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @GetMapping("/station/{id}")
    public ResponseEntity<Optional<LineStation>> getStation(@PathVariable long id)  throws NotFoundException, StationNoFoundException {
        logger.info("Begin get station by Id");
        Optional<LineStation> stationId = lineStationService.findById(id);
        logger.info("Finish get station by Id");
        if (stationId.isPresent()) {
            return new ResponseEntity<>(stationId, HttpStatus.OK);
        } else {
            throw new NotFoundException("Station not found with id " + id);
        }
    }

    @PostMapping("/station/{lineId}/station")
    public ResponseEntity<LineStation> addOneStation(@PathVariable long lineId, @RequestBody LineStation lineStation) throws NotFoundException {
        try{
            logger.info("Begin add station by Line Id");
            LineStation newStation = lineStationService.addStation(lineId, lineStation);
            logger.info("Finish add station by Line Id");
            return ResponseEntity.status(HttpStatus.CREATED).body(newStation);
        }catch (NotFoundException nfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping("/stations/{id}")
    public ResponseEntity<LineStation> modStation(@PathVariable long id, @RequestBody LineStation lineStation) throws NotFoundException {
        logger.info("Begin modify station by Id");
        try {
            LineStation changeStation = lineStationService.modStation(id, lineStation);
            logger.info("Finish modify station by Id");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeStation);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PatchMapping("/station/{stationId}/station")
    public ResponseEntity<LineStation> updateStation(@PathVariable long stationId, @RequestBody @Validated inStationDTO lineStation) throws NotFoundException {
        logger.info("Begin update partialy station by station id");
        try{
            LineStation updateStation = lineStationService.updateOneStation(stationId, lineStation);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateStation);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<Void> delOneStation(@PathVariable long id) throws LineNoFoundException {
        logger.info("Begin delete station by Id");
        try {
            lineStationService.delStation(id);
            logger.info("Finish delete station by Id");
            return ResponseEntity.noContent().build();
        } catch (LineNoFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
