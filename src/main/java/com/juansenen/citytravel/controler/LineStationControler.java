package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inStationDTO;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LineStationControler {

    @Autowired
    LineStationService lineStationService;

    @GetMapping("/station")
    public ResponseEntity<List<outStationDTO>> getAll(@RequestParam(name="wifi",defaultValue = "",required = false) String wifi,
                                                      @RequestParam(name="busStation", defaultValue = "",required = false) String busStation,
                                                      @RequestParam(name="taxiStation", defaultValue = "", required = false) String taxiStation){
        if (wifi.equals("") && busStation.equals("") && taxiStation.equals("")){
            return ResponseEntity.ok(lineStationService.findAll());
        }
        boolean hasBus = Boolean.parseBoolean(busStation);
        boolean haswifi = Boolean.parseBoolean(wifi);
        boolean hasTaxi = Boolean.parseBoolean(taxiStation);
        return ResponseEntity.ok(lineStationService.findAllStationWithWifiBusAndTaxi(haswifi, hasBus, hasTaxi));
    }
    @GetMapping("/station/{id}")
    public ResponseEntity<Optional<LineStation>> getStation(@PathVariable long id) throws StationNoFoundException {
       Optional<LineStation> stationId = lineStationService.findById(id);
        return new ResponseEntity<>(stationId, HttpStatus.OK);
    }

    @PostMapping("/station/{lineId}/station")
    public ResponseEntity<LineStation> addOneStation(@PathVariable long lineId, @RequestBody inStationDTO inStationDTO) throws LineNoFoundException {
        LineStation newStation = lineStationService.addStation(lineId, inStationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStation);
    }


    @PutMapping("/station/{id}")
    public ResponseEntity<LineStation> modStation(@PathVariable long id, @RequestBody LineStation lineStation) throws LineNoFoundException {
        LineStation changeStation = lineStationService.modStation(id, lineStation);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeStation);
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity<Void> delOneStation(@PathVariable long id) throws LineNoFoundException {
        LineStation stationDel = lineStationService.delStation(id);
        return ResponseEntity.noContent().build();
    }
}
