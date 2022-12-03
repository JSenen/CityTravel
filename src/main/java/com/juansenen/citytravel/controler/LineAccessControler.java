package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.dto.inAccessDTO;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.service.LineAccessService;
import com.juansenen.citytravel.service.LineStationService;
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
    @Autowired
    LineStationService lineStationService;

    @GetMapping("/access")
    public ResponseEntity<List<outAccessDTO>> getAll(@RequestParam(name = "elevator",defaultValue = "",required = false) String elev){
        if (elev.equals("")){
            return ResponseEntity.ok(lineAccessService.findAll());
        }
        boolean elevator = Boolean.parseBoolean(elev);
        return ResponseEntity.ok(lineAccessService.searchAccessWithElevator(elevator));
    }

    @GetMapping("/access/{id}")
    public ResponseEntity<Optional<LineAccess>> getById(@PathVariable long id){
        Optional<LineAccess> accessId = lineAccessService.findById(id);
        return new ResponseEntity<>(accessId, HttpStatus.OK);
    }

    @PostMapping("/access/{stationid}/access")
    public ResponseEntity<LineAccess> addOneAccess(@PathVariable long stationid, @RequestBody inAccessDTO inAccessDTO) throws NotFoundException {
        LineAccess newOneAccess = lineAccessService.addNewAccessByStation(inAccessDTO, stationid);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOneAccess);
    }

    @PutMapping("/access/{id}")
    public ResponseEntity<LineAccess> modyAccess(@PathVariable long id, @RequestBody LineAccess lineAccess) throws NotFoundException {
        LineAccess changeAccess = lineAccessService.modyAccess(id, lineAccess);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeAccess);
    }

    @DeleteMapping("/access/{id}")
    public ResponseEntity<Void> delOneAccess(@PathVariable long id) throws NotFoundException {
        lineAccessService.delAccess(id);
        return ResponseEntity.noContent().build();
    }
}
