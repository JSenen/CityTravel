package com.juansenen.citytravel.controler;


import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.dto.inGarageDTO;
import com.juansenen.citytravel.domain.dto.outGarageDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.service.LineGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LineGarageControler {
    @Autowired
    LineGarageService lineGarageService;

    @GetMapping("/garage")
    public ResponseEntity<List<outGarageDTO>> getAll(@RequestParam(name = "taller",defaultValue = "",required = false) String taller,
                                                     @RequestParam(name ="rrhh",defaultValue = "",required = false) String rechum,
                                                     @RequestParam(name = "paintservice",defaultValue = "",required = false) String paint){
        if (taller.equals("") && rechum.equals("") && paint.equals("")){
            return ResponseEntity.ok(lineGarageService.findAll());
        }
        boolean mechanic = Boolean.parseBoolean(taller);
        boolean rrhh = Boolean.parseBoolean(rechum);
        boolean pService = Boolean.parseBoolean(paint);

        return ResponseEntity.ok(lineGarageService.searchByTallerOrRecHumOrPaintService(mechanic, rrhh, pService));
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<Optional<LineGarage>> getById(@PathVariable long id){
        Optional<LineGarage> garageId = lineGarageService.findById(id);
        return new ResponseEntity<>(garageId, HttpStatus.OK);
    }

    @PostMapping("/garage/{stationId}/garage")
    public ResponseEntity<LineGarage> addOneStation(@PathVariable long stationId, @RequestBody inGarageDTO inGarageDTO) throws StationNoFoundException {
        LineGarage newGarage = lineGarageService.addNewGarByLine(inGarageDTO, stationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGarage);
    }
    @PutMapping("/garage/{id}")
    public ResponseEntity<LineGarage> modyGarage(@PathVariable long id, @RequestBody LineGarage lineGarage) throws LineNoFoundException {
        LineGarage changeGarage = lineGarageService.modGarage(id, lineGarage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeGarage);
    }
    @DeleteMapping("/garage/{garageId}")
    public ResponseEntity<LineGarage> delGarage(@PathVariable long garageId) throws LineNoFoundException {
        LineGarage lineGarage = lineGarageService.deleteGarage(garageId);
        return ResponseEntity.noContent().build();
    }

}
