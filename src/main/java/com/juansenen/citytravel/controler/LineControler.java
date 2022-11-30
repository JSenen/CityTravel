package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineService;
import com.juansenen.citytravel.service.LineStationService;
import com.juansenen.citytravel.service.LineTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class LineControler {

    @Autowired
    private LineService lineService;
    @Autowired
    private LineTrainService lineTrainService;
    @Autowired
    private LineStationService lineStationService;


    //Listar todos
    @GetMapping("/line")
    public ResponseEntity<List<Line>> getAll(){

            return ResponseEntity.ok(lineService.findAll());
    }
    //Buscar por id
    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLine(@PathVariable long id) throws LineNoFoundException {
        Line line = lineService.findById(id);
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    @GetMapping("/line/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId(@PathVariable long lineId) throws LineNoFoundException {
        Line line = lineService.findById(lineId);
        List<LineTrain> trains = null;
        trains = lineTrainService.findByLineId(line);
        return ResponseEntity.ok(trains);
    }
    //Grabar linea
    @PostMapping("/line")
    public ResponseEntity<Line> addLine(@RequestBody Line line) throws LineNoFoundException{
        Line newline = lineService.add(line);
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }
    @PostMapping("/station/{lineId}/station")
    public ResponseEntity<LineStation> addOneLine(@PathVariable long lineId, @RequestBody LineStation lineStation) throws LineNoFoundException {
        LineStation newStation = lineStationService.addNewStationByLine(lineStation, lineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStation);
    }
    //Borrar uno

    @DeleteMapping("/line/{id}")
    public ResponseEntity<Void> delLine(@PathVariable long id) throws LineNoFoundException{
         lineService.deleteLine(id);
         return ResponseEntity.noContent().build();
    }
    //Modificar 1 por id
    @PutMapping("/line/{id}")
    public  ResponseEntity<Line> modLine (@PathVariable long id, @RequestBody Line line) throws LineNoFoundException {
        Line lineModif = lineService.modyLine(id, line);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lineModif);
    }
    @ExceptionHandler(LineNoFoundException.class)
    public ResponseEntity<ErrorMessage> lineNoFoundException(LineNoFoundException lnfe){
        ErrorMessage errorMessage = new ErrorMessage(404, lnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(StationNoFoundException.class)
    public ResponseEntity<ErrorMessage> stationNoFound(StationNoFoundException snfe){
        ErrorMessage errorMessage = new ErrorMessage(404, snfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException (MethodArgumentNotValidException manve){
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request",errors);
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<ErrorMessage> handleException (Exception exc){

    //    ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
    //    return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    //}

}
