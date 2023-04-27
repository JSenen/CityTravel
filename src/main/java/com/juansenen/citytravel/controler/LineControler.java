package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.outLineDTO;
import com.juansenen.citytravel.exception.*;
import com.juansenen.citytravel.service.LineService;
import com.juansenen.citytravel.service.LineStationService;
import com.juansenen.citytravel.service.LineTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.NumberFormatException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class LineControler {

    @Autowired
    private LineService lineService;
    @Autowired
    private LineTrainService lineTrainService;
    @Autowired
    private LineStationService lineStationService;

    private final Logger logger = LoggerFactory.getLogger(LineControler.class);


    //Listar todos
    @GetMapping("/lines")
    public ResponseEntity<List<outLineDTO>> getAll(@RequestParam(name = "firstTime", defaultValue = "00:00",required = false) String start,
                                                   @RequestParam(name = "lastTime", defaultValue = "00:00", required = false) String close){
        logger.info("Begin getLine");
        //por defecto se le asigna 00:00, para que cuente ese valor en caso de que solo se selccione una de las posibilidades
        if (start.equals("00:00") && close.equals("00:00")){
            logger.info("Finish getLine");
            return ResponseEntity.ok(lineService.findAll());
        }
        logger.debug("Begin getLine by Hours");
        LocalTime hstart = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime hclose = LocalTime.parse(close, DateTimeFormatter.ofPattern("HH:mm"));
        logger.info("Finish getLine by Hours");
        return ResponseEntity.ok(lineService.searchByHourStartAndHourClose(hstart, hclose));

    }

    //Buscar por id
    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLine(@PathVariable("id") @Min(1L) long id) throws NotFoundException, LineNoFoundException {
        logger.info("Begin getLine By Id");
        try {
            Long.parseLong(String.valueOf(id));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be a number", e);
        }
        Line line = lineService.findById(id);
        logger.info("Finish getLine By Id");
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    @GetMapping("/line/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId(@PathVariable long lineId) throws NotFoundException, LineNoFoundException {
        logger.info("Begin getLine trains by Id Line");
        Line line = lineService.findById(lineId);
        List<LineTrain> trains = null;
        trains = lineTrainService.findByLineId(lineId);
        logger.info("Finsh getLine trains by Id Line");
        return ResponseEntity.ok(trains);
    }
    /** Filtrar por datos las estaciones */
    @GetMapping("/line/{lineId}/stations")
    public ResponseEntity<List<LineStation>> getStationsByLineID(@PathVariable long lineId,@RequestParam(name="wifi",defaultValue = "",required = false) String wifi,
                                                                 @RequestParam(name="busStation", defaultValue = "",required = false) String busStation,
                                                                 @RequestParam(name="taxiStation", defaultValue = "", required = false) String taxiStation,
                                                                 @RequestParam(name="ptoInfo", defaultValue = "", required = false) String ptoInfo) throws NotFoundException, LineNoFoundException {
        logger.info("Begin getLine stations by Id Line");
        if (wifi.equals("") && busStation.equals("") && taxiStation.equals("") && ptoInfo.equals("")){
            Line line = lineService.findById(lineId);
            List<LineStation> stations = null;
            stations = lineStationService.findByLineId(lineId);
            return ResponseEntity.ok(stations);
        }
        boolean hasBus = Boolean.parseBoolean(busStation);
        boolean haswifi = Boolean.parseBoolean(wifi);
        boolean hasTaxi = Boolean.parseBoolean(taxiStation);
        boolean hasPtoInfo = Boolean.parseBoolean(ptoInfo);
        List<LineStation> stationsParams = new ArrayList<>();
        stationsParams = lineStationService.findStationsByParams(lineId,haswifi,hasBus,hasTaxi,hasPtoInfo);
        logger.info("Finsh getLine trains by Id Line");
        return ResponseEntity.ok(stationsParams);
    }
    //Grabar linea
    @PostMapping("/line") /** @Validated y MethodArgumentNotValidException para validar entradas error 400 BadRequest */
    public ResponseEntity<Line> addLine(@RequestBody @Validated Line line) throws MethodArgumentNotValidException{
        logger.info("Begin add new Line");
        Line newline = lineService.add(line);
        logger.info("Finish add new Line");
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }
    //Actualizacion parcial

    @PatchMapping("/lines/{id}")
    public ResponseEntity<Line> updateLine(@PathVariable long id, @RequestBody Line line) throws LineNoFoundException {
        try{
            logger.info("Begin update partialy line by Id");
            Line updateLine = lineService.updateLine(id, line);
            logger.info("End update partialy line by Id");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateLine);
        }catch (NotFoundException nfe){
            ErrorResponse error = new ErrorResponse(404,"Line ID not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    //Borrar uno

    @DeleteMapping("/lines/{id}")
    public ResponseEntity<Void> delLine(@PathVariable long id) throws LineNoFoundException {
        try {
            logger.info("Begin delete a Line");
            lineService.deleteLine(id);
            logger.info("Finish delete a Line");
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID format", e);
        } catch (LineNoFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Line not found", e);
        }
    }
    //Modificar 1 por id
    @PutMapping("/lines/{id}")
    public  ResponseEntity<Line> modLine (@PathVariable long id ,@Valid @RequestBody Line line) throws LineNoFoundException {
        logger.info("Begin modify a Line by Id");
        Line lineModif = lineService.modyLine(id, line);
        logger.info("Finish modify a Line by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lineModif);
    }
    //Metodo de excepción genérica para todas las clases. Recoge la clase que produce la excepción
    // y la incorpora al mensaje
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException nfexc){
        logger.error(nfexc.getMessage(), nfexc);
        ErrorMessage errorMessage = new ErrorMessage(404, nfexc.getMessage());
        logger.error("Finish NotFoundException");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LineNoFoundException.class)
    public ResponseEntity<ErrorMessage> lineNoFoundException(LineNoFoundException lnfe){
        logger.error(lnfe.getMessage(), lnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, lnfe.getMessage());
        logger.error("Finish NotFoundException");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNumberFormatException(NumberFormatException nfe){
        logger.error(nfe.getMessage(), nfe);
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        logger.error("Finish NotFoundException");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException (MethodArgumentNotValidException manve){
        logger.error(manve.getMessage(), manve);
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request",errors);
        logger.error("Finish 400 Bad Request exception");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException (Exception exc){
        logger.error(exc.getMessage(), exc);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        logger.error("Finish 500 Internal Server error");
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
