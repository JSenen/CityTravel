package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inLineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineStationControler {

    @Autowired
    LineStationService lineStationService;

    @GetMapping("/station")
    public ResponseEntity<List<LineStation>> getAll(){
        return ResponseEntity.ok(lineStationService.findAll());
    }

    @GetMapping("/station/{id}")
    public ResponseEntity<Optional<LineStation>> getStation(@PathVariable long id) throws StationNoFoundException {
       Optional<LineStation> stationId = lineStationService.findById(id);
        return new ResponseEntity<>(stationId, HttpStatus.OK);
    }

    @PostMapping("/station")
    public ResponseEntity<LineStation> addOneStation(@RequestBody LineStation lineStation){
        LineStation newStation = lineStationService.addStation(lineStation);
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
