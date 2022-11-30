package com.juansenen.citytravel.controler;


import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineGarageControler {
    @Autowired
    LineGarageService lineGarageService;

    @GetMapping("/garage")
    public ResponseEntity<List<LineGarage>> getAll(){
        return ResponseEntity.ok(lineGarageService.findAll());
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<Optional<LineGarage>> getById(@PathVariable long id){
        Optional<LineGarage> garageId = lineGarageService.findById(id);
        return new ResponseEntity<>(garageId, HttpStatus.OK);
    }

    @PostMapping("/garage")
    public ResponseEntity<LineGarage> addGarage(@RequestBody LineGarage lineGarage) throws StationNoFoundException {
        LineGarage newLineGarage = lineGarageService.addGarage(lineGarage);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLineGarage);
    }
    @PostMapping("/garage/{stationId}/garage")
    public ResponseEntity<LineGarage> addOneTrain(@PathVariable long stationId, @RequestBody LineGarage lineGarage) throws StationNoFoundException {
        LineGarage newGarage = lineGarageService.addNewGarByLine(lineGarage, stationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGarage);
    }
    @PutMapping("/garage/{id}")
    public ResponseEntity<LineGarage> modyGarage(@PathVariable long id, @RequestBody LineGarage lineGarage) throws LineNoFoundException {
        LineGarage changeGarage = lineGarageService.modGarage(id, lineGarage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeGarage);
    }

    @DeleteMapping("/garage/{id}")
    public ResponseEntity<Void> delOneGarage(@PathVariable long id) throws LineNoFoundException {
        LineGarage delOneGarage = lineGarageService.delGarage(id);
        return ResponseEntity.noContent().build();
    }
}
