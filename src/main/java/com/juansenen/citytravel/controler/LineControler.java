package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.outLineDTO;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<outLineDTO>> getAll(@RequestParam(name = "firstTime", defaultValue = "00:00",required = false) String start,
                                                   @RequestParam(name = "lastTime", defaultValue = "00:00", required = false) String close){
        //por defecto se le asigna 00:00, para que cuente ese valor en caso de que solo se selccione una de las posibilidades
        if (start.equals("00:00") && close.equals("00:00")){
            return ResponseEntity.ok(lineService.findAll());
        }
        LocalTime hstart = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime hclose = LocalTime.parse(close, DateTimeFormatter.ofPattern("HH:mm"));
        return ResponseEntity.ok(lineService.searchByHourStartAndHourClose(hstart, hclose));

    }

    //Buscar por id
    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLine(@PathVariable long id) throws NotFoundException {
        Line line = lineService.findById(id);
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    @GetMapping("/line/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId(@PathVariable long lineId) throws  NotFoundException {
        Line line = lineService.findById(lineId);
        List<LineTrain> trains = null;
        trains = lineTrainService.findByLineId(lineId);
        return ResponseEntity.ok(trains);
    }
    //Grabar linea
    @PostMapping("/line")
    public ResponseEntity<Line> addLine(@RequestBody Line line){
        Line newline = lineService.add(line);
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }
    //Borrar uno

    @DeleteMapping("/line/{id}")
    public ResponseEntity<Void> delLine(@PathVariable long id) throws NotFoundException {
        lineService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }
    //Modificar 1 por id
    @PutMapping("/line/{id}")
    public  ResponseEntity<Line> modLine (@PathVariable long id, @RequestBody Line line) throws NotFoundException {
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException (Exception exc){

        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
