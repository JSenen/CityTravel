package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.service.LineAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineAccessControler {

    @Autowired
    LineAccessService lineAccessService;

    @GetMapping("/lineaccess")
    public ResponseEntity<List<LineAccess>> getAll(){
        return ResponseEntity.ok(lineAccessService.findAll());
    }

    @GetMapping("/lineaccess/{id}")
    public ResponseEntity<Optional<LineAccess>> getById(@PathVariable long id){
        Optional<LineAccess> accessId = lineAccessService.findById(id);
        return new ResponseEntity<>(accessId, HttpStatus.OK);
    }

    @PostMapping("/lineaccess")
    public ResponseEntity<LineAccess> newAccess(@RequestBody LineAccess lineAccess){
        LineAccess newlinaccess = lineAccessService.addAccess(lineAccess);
        return ResponseEntity.status(HttpStatus.CREATED).body(newlinaccess);
    }
    @PutMapping("/lineaccess/{id}")
    public ResponseEntity<LineAccess> modyAccess(@PathVariable long id, @RequestBody LineAccess lineAccess) throws LineNoFoundException {
        LineAccess changeAccess = lineAccessService.modyAccess(id, lineAccess);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeAccess);
    }

    @DeleteMapping("/lineaccess/{id}")
    public ResponseEntity<Void> delOneAccess(@PathVariable long id) throws LineNoFoundException {
        LineAccess accesdel = lineAccessService.delAccess(id);
        return ResponseEntity.noContent().build();
    }
}
