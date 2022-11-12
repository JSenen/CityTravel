package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineControler {

    @Autowired
    LineService lineService;

    //Listar todos
    @GetMapping("/line")
    public List<Line> getLines(){
        return lineService.findAll();
    }

    //Buscar por id
    @GetMapping("/line/{id}")
    public Line getLine(@PathVariable long id){
        return lineService.findById(id);
    }


    //Grabar linea
    @PostMapping("/line")
    public Line addLine(@RequestBody Line line){
        Line newline = lineService.add(line);
        return newline;
    }

    //Borrar uno

    @DeleteMapping("/line/{id}")
    public void delLine(@PathVariable long id){
         lineService.deleteLine(id);
    }

    //Modificar uno
    @PatchMapping("/line/{id}")
    public void modLine(@PathVariable long id, @RequestBody Line line){
        lineService.modiLine(id, line);
    }


}
