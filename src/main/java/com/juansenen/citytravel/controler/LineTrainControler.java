package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.TrainDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.service.LineService;
import com.juansenen.citytravel.service.LineTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineTrainControler {

    @Autowired
    private LineTrainService lineTrainService;
    @Autowired
    private LineService lineService;

    @GetMapping("/train")
    public ResponseEntity<List<LineTrain>> getAll(){
        return ResponseEntity.ok(lineTrainService.findAll());
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<Optional<LineTrain>> getById(@PathVariable long id) throws LineNoFoundException {
        Optional<LineTrain> trainId = lineTrainService.findById(id);
        return new ResponseEntity<>(trainId, HttpStatus.OK);
    }
    @GetMapping("/train/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId (@PathVariable long lineId){
        List<LineTrain> trainList = lineTrainService.findByLineId(lineId);
        return new ResponseEntity<>(trainList, HttpStatus.OK);
    }

    @PostMapping("/train/{lineId}/train")
    public ResponseEntity<LineTrain> addOneTrain(@PathVariable long lineId, @RequestBody LineTrain lineTrain) throws LineNoFoundException {
        LineTrain newTrain = lineTrainService.addNewTrain(lineTrain, lineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrain);
    }
    @PutMapping("/train/{id}")
    public ResponseEntity<LineTrain> modTrain(@PathVariable long id, @RequestBody LineTrain lineTrain) throws LineNoFoundException {
        LineTrain changeTrain = lineTrainService.modTrain(id, lineTrain);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeTrain);
    }

    @DeleteMapping("/train/{id}")
    public ResponseEntity<Void> delOneTrain(@PathVariable long id) throws LineNoFoundException {
        LineTrain delTrain = lineTrainService.delTrain(id);
        return ResponseEntity.noContent().build();
    }
}
