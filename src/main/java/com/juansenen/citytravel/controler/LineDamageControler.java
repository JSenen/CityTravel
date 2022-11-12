package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineDamage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.service.LineDamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LineDamageControler {

    @Autowired
    LineDamageService lineDamageService;

    @GetMapping("/linedamage")
    public ResponseEntity<List<LineDamage>> getAll(){
        return new ResponseEntity<>(lineDamageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/linedamage/{id}")
    public ResponseEntity<LineDamage> getById(@PathVariable long id) throws LineNoFoundException {
        return new ResponseEntity<>(lineDamageService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/linedamage")
    public ResponseEntity<LineDamage> addOne(@Valid @RequestBody LineDamage lineDamage){
        return new ResponseEntity<>(lineDamageService.add(lineDamage), HttpStatus.CREATED);
    }

    @DeleteMapping("/linedamage/{id}")
    public ResponseEntity<Void> delOne(@PathVariable long id) throws LineNoFoundException {
        lineDamageService.delDamage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/linedamage/{id}")
    public ResponseEntity<LineDamage> modOne(@PathVariable long id, @RequestBody LineDamage lineDamage) throws LineNoFoundException {
        LineDamage modLineDamage = lineDamageService.modDamage(id, lineDamage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modLineDamage);
    }
}
