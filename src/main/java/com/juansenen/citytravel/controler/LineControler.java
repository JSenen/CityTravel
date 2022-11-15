package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LineControler {

    @Autowired
    LineService lineService;

    //Listar todos
    @GetMapping("/line")
    public ResponseEntity<List<Line>> getAll() throws LineNoFoundException {

            return ResponseEntity.ok(lineService.findAll());
    }

    //Buscar por id
    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLine(@PathVariable long id) throws LineNoFoundException {
        Line line = lineService.findById(id);
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    //Grabar linea
    @PostMapping("/line")
    public ResponseEntity<Line> addLine(@Valid @RequestBody Line line) throws LineNoFoundException{
        Line newline = lineService.add(line);
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }

    //Borrar uno

    @DeleteMapping("/line/{id}")
    public ResponseEntity<Void> delLine(@PathVariable long id) throws LineNoFoundException{
         lineService.deleteLine(id);
         return ResponseEntity.noContent().build();
    }
    //Modificar 1 por id
    @PutMapping("/line/{id}")
    public  ResponseEntity<Line> modLine (@PathVariable long id,@RequestBody Line line) throws LineNoFoundException {
        Line lineModif = lineService.modyLine(id, line);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lineModif);
    }
    @ExceptionHandler(LineNoFoundException.class)
    public ResponseEntity<ErrorMessage> lineNoFoundException(LineNoFoundException lnfe){
        ErrorMessage errorMessage = new ErrorMessage(404, lnfe.getMessage());
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
