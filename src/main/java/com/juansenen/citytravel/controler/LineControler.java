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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
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

    private final Logger logger = LoggerFactory.getLogger(LineControler.class);


    //Listar todos
    @GetMapping("/line")
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
    public ResponseEntity<Line> getLine(@PathVariable long id) throws NotFoundException {
        logger.info("Begin getLine By Id");
        Line line = lineService.findById(id);
        logger.info("Finish getLine By Id");
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    @GetMapping("/line/{lineId}/trains")
    public ResponseEntity<List<LineTrain>> getTrainsByLineId(@PathVariable long lineId) throws  NotFoundException {
        logger.info("Begin getLine trains by Id Line");
        Line line = lineService.findById(lineId);
        List<LineTrain> trains = null;
        trains = lineTrainService.findByLineId(lineId);
        logger.info("Finsh getLine trains by Id Line");
        return ResponseEntity.ok(trains);
    }
    //Grabar linea
    @PostMapping("/line")
    public ResponseEntity<Line> addLine(@RequestBody Line line){
        logger.info("Begin add new Line");
        Line newline = lineService.add(line);
        logger.info("Finish add new Line");
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }
    //Actualizacion parcial

    @PatchMapping("/line/{id}")
    public ResponseEntity<Line> updateLine(@PathVariable long id, @RequestBody Line line) throws NotFoundException {
        logger.info("Begin update partialy line by Id");
        Line updateLine = lineService.updateLine(id, line);
        logger.info("End update partialy line by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateLine);
    }
    //Borrar uno

    @DeleteMapping("/line/{id}")
    public ResponseEntity<Void> delLine(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete a Line");
        lineService.deleteLine(id);
        logger.info("Finish delete a Line");
        return ResponseEntity.noContent().build();
    }
    //Modificar 1 por id
    @PutMapping("/line/{id}")
    public  ResponseEntity<Line> modLine (@PathVariable long id, @RequestBody Line line) throws NotFoundException {
        logger.info("Begin modify a Line by Id");
        Line lineModif = lineService.modyLine(id, line);
        logger.info("Finish modify a Line by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lineModif);
    }
    //Metodo de excepci??n gen??rica para todas las clases. Recoge la clase que produce la excepci??n
    // y la incorpora al mensaje
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException nfexc){
        logger.error(nfexc.getMessage(), nfexc);
        ErrorMessage errorMessage = new ErrorMessage(404, nfexc.getMessage());
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
        logger.error("Finish 4000 Bad Request exception");
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
