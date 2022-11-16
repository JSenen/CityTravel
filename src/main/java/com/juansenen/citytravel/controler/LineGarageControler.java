package com.juansenen.citytravel.controler;


import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.dto.LineGarageDTO;
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

    @GetMapping("/linegarage")
    public ResponseEntity<List<LineGarage>> getAll(){
        return ResponseEntity.ok(lineGarageService.findAll());
    }

    @GetMapping("/linegarage/{id}")
    public ResponseEntity<Optional<LineGarage>> getById(@PathVariable long id){
        Optional<LineGarage> garageId = lineGarageService.findById(id);
        return new ResponseEntity<>(garageId, HttpStatus.OK);
    }

    @PostMapping("/linegarage")
    public ResponseEntity<LineGarage> newAccess(@RequestBody LineGarageDTO lineGarageDTO) throws StationNoFoundException {
        LineGarage newLineGarage = lineGarageService.addGarage(lineGarageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLineGarage);
    }
    @PutMapping("/linegarage/{id}")
    public ResponseEntity<LineGarage> modyGarage(@PathVariable long id, @RequestBody LineGarage lineGarage) throws LineNoFoundException {
        LineGarage changeGarage = lineGarageService.modGarage(id, lineGarage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeGarage);
    }

    @DeleteMapping("/linegarage/{id}")
    public ResponseEntity<Void> delOneGarage(@PathVariable long id) throws LineNoFoundException {
        LineGarage delOneGarage = lineGarageService.delGarage(id);
        return ResponseEntity.noContent().build();
    }
}
