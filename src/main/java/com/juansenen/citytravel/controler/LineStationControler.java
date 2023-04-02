package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inStationDTO;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
                                                      @RequestParam(name="taxiStation", defaultValue = "", required = false) String taxiStation){
        logger.info("Begin get station with or without @RequestParam");
        if (wifi.equals("") && busStation.equals("") && taxiStation.equals("")){
            logger.info("Finish get station without @RequestParam");
            return ResponseEntity.ok(lineStationService.findAll());
        }
        boolean hasBus = Boolean.parseBoolean(busStation);
        boolean haswifi = Boolean.parseBoolean(wifi);
        boolean hasTaxi = Boolean.parseBoolean(taxiStation);
        logger.info("Finish get station with @RequestParam");
        return ResponseEntity.ok(lineStationService.findAllStationWithWifiBusAndTaxi(haswifi, hasBus, hasTaxi));
    }
    @GetMapping("/station/{id}")
    public ResponseEntity<Optional<LineStation>> getStation(@PathVariable long id) throws StationNoFoundException {
        logger.info("Begin get station by Id");
        Optional<LineStation> stationId = lineStationService.findById(id);
        logger.info("Finish get station by Id");
        return new ResponseEntity<>(stationId, HttpStatus.OK);
    }

    @PostMapping("/station/{lineId}/station")
    public ResponseEntity<LineStation> addOneStation(@PathVariable long lineId, @RequestBody inStationDTO inStationDTO) throws NotFoundException {
        logger.info("Begin add station by Line Id");
        LineStation newStation = lineStationService.addStation(lineId, inStationDTO);
        logger.info("Finish add station by Line Id");
        return ResponseEntity.status(HttpStatus.CREATED).body(newStation);
    }


    @PutMapping("/station/{id}")
    public ResponseEntity<LineStation> modStation(@PathVariable long id, @RequestBody LineStation lineStation) throws NotFoundException {
        logger.info("Begin modify station by Id");
        LineStation changeStation = lineStationService.modStation(id, lineStation);
        logger.info("Finish modify station by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeStation);
    }
    @PatchMapping("/station/{stationId}/station")
    public ResponseEntity<LineStation> updateStation(@PathVariable long stationId, @RequestBody LineStation lineStation) throws NotFoundException {
        logger.info("Begin update partialy station by station id");
        LineStation updateStation = lineStationService.updateOneStation(stationId, lineStation);
        logger.info("End update partialy station by station id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateStation);
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity<Void> delOneStation(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete station by Id");
        lineStationService.delStation(id);
        logger.info("Finish delete station by Id");
        return ResponseEntity.noContent().build();
    }
}
