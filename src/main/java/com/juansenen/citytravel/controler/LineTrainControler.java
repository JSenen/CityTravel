package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.inTrainDTO;
import com.juansenen.citytravel.domain.dto.outTrainDTO;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.ErrorResponse;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.service.LineService;
import com.juansenen.citytravel.service.LineTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class LineTrainControler {

    @Autowired
    private LineTrainService lineTrainService;

    private final Logger logger = LoggerFactory.getLogger(LineTrainControler.class);

    @GetMapping("/trains")
    public ResponseEntity<List<LineTrain>> getAll(@RequestParam (name = "numWagons",defaultValue = "0",required = false) String wagons,
                                                    @RequestParam (name = "numSeats",defaultValue = "0", required = false) String seats,
                                                    @RequestParam (name = "numStandUp",defaultValue = "0",required = false) String standup){
        logger.info("Begin get trains with or without @ReuestParam");
        //Para que H2 reconozca el parametro, debe introducirse como defecto H2 No reconoce NUll en el Query
        if (wagons.equals("0") && seats.equals("0") && standup.equals("0")){
            //Si no se realiza ningun @Query, se listan todos
            logger.info("Finish get trains withput @RequestParam");
            return ResponseEntity.ok(lineTrainService.findAll());
        }
        //Pasamos el parametro al tipo del atributo para que lo reconzca H2
        int numWagons = Integer.parseInt(wagons);
        int numSeats = Integer.parseInt(seats);
        int numStandUp = Integer.parseInt(standup);
        logger.info("Finish get trains with @RequesParam");
        return ResponseEntity.ok(lineTrainService.searchByWagonsOrSeatsOrStandUp(numWagons, numSeats, numStandUp));
    }

    @GetMapping("/trains/{id}")
    public ResponseEntity<Optional<LineTrain>> getById(@PathVariable long id) throws LineNoFoundException {

        logger.info("Begin get train by Id");
        Optional<LineTrain> trainId = lineTrainService.findById(id);
        logger.info("Finish get train by Id");
        if (trainId.isEmpty()) {
            ErrorResponse error = new ErrorResponse(404, "Train not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trainId);
        }
        return new ResponseEntity<>(trainId, HttpStatus.OK);
    }
    @GetMapping("/train/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId (@PathVariable long lineId){
        logger.info("Begin get train by Line Id");
        List<LineTrain> trainList = lineTrainService.findByLineId(lineId);
        logger.info("Finish get train by Line Id");
        return new ResponseEntity<>(trainList, HttpStatus.OK);
    }

    @PostMapping("/train/{lineId}/train")
    public ResponseEntity<LineTrain> addOneTrainWithGarage(@PathVariable long lineId, @RequestBody LineTrain lineTrain) throws LineNoFoundException{
        try {
            LineTrain newTrain = lineTrainService.addNewTrain(lineId, lineTrain);
            logger.info("Finish add train by Id");
            return ResponseEntity.status(HttpStatus.CREATED).body(newTrain);
        } catch (LineNoFoundException e) {
            ErrorResponse error = new ErrorResponse(404,"Line ID not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/train/{id}")
    public ResponseEntity<LineTrain> modTrain(@PathVariable long id, @RequestBody LineTrain lineTrain) throws  NotFoundException {
        try{
            logger.info("Begin modify train by Id");
            LineTrain changeTrain = lineTrainService.modTrain(id, lineTrain);
            logger.info("Finsih modify train by Id");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeTrain);
        }catch (NotFoundException nfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PatchMapping("/train/{trainId}")
    public ResponseEntity<LineTrain> updateTrain(@PathVariable long trainId, @RequestBody LineTrain lineTrain) throws NotFoundException {
        try{
            logger.info("Begin update partialy train by train id");
            LineTrain updtrain = lineTrainService.updateOneTrain(trainId, lineTrain);
            logger.info("End update partialy train by train id");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updtrain);
        }catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }

    @DeleteMapping("/train/{id}")
    public ResponseEntity<Void> delOneTrain(@PathVariable long id) throws NotFoundException {
        try{
            logger.info("Begin delete train by Id");
            lineTrainService.delTrain(id);
            logger.info("Finish get train by Id");
            return ResponseEntity.noContent().build();
        }catch (NotFoundException nfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
