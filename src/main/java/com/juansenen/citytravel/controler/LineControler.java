package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.exception.ErrorMessage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineControler {

    @Autowired
    LineService lineService;

    //Listar todos
    @GetMapping("/line")
    public ResponseEntity<List<Line>> getLines(){
        return new ResponseEntity<>(lineService.findAll(), HttpStatus.OK);
    }

    //Buscar por id
    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLine(@PathVariable long id) throws LineNoFoundException {
        Line line = lineService.findById(id);
        return new ResponseEntity<>(line,HttpStatus.OK);
    }
    //Grabar linea
    @PostMapping("/line")
    public ResponseEntity<Line> addLine(@RequestBody Line line) throws LineNoFoundException{
        Line newline = lineService.add(line);
        return ResponseEntity.status(HttpStatus.CREATED).body(newline);
    }

    //Borrar uno

    @DeleteMapping("/line/{id}")
    public void delLine(@PathVariable long id) throws LineNoFoundException{
         lineService.deleteLine(id);
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


    //Modificar uno
    @PatchMapping("/line/{id}")
    public void modLine(@PathVariable long id, @RequestBody Line line){
        lineService.modiLine(id, line);
    }


}
