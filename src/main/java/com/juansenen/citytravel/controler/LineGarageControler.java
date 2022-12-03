package com.juansenen.citytravel.controler;


import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.dto.inGarageDTO;
import com.juansenen.citytravel.domain.dto.outGarageDTO;

import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.service.LineGarageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(LineGarageControler.class);

    @GetMapping("/garage")
    public ResponseEntity<List<outGarageDTO>> getAll(@RequestParam(name = "taller",defaultValue = "",required = false) String taller,
                                                     @RequestParam(name ="rrhh",defaultValue = "",required = false) String rechum,
                                                     @RequestParam(name = "paintservice",defaultValue = "",required = false) String paint){
        logger.info("Begin get garage with or without @RequestParam");
        if (taller.equals("") && rechum.equals("") && paint.equals("")){
            logger.info("Finish get garage without @RequesParam");
            return ResponseEntity.ok(lineGarageService.findAll());
        }
        boolean mechanic = Boolean.parseBoolean(taller);
        boolean rrhh = Boolean.parseBoolean(rechum);
        boolean pService = Boolean.parseBoolean(paint);
        logger.info("Finish get garage with @RequstParam");

        return ResponseEntity.ok(lineGarageService.searchByTallerOrRecHumOrPaintService(mechanic, rrhh, pService));
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<Optional<LineGarage>> getById(@PathVariable long id){
        logger.info("Begin get garage by Id");
        Optional<LineGarage> garageId = lineGarageService.findById(id);
        logger.info("Finish get garage by Id");
        return new ResponseEntity<>(garageId, HttpStatus.OK);
    }

    @PostMapping("/garage/{stationId}/garage")
    public ResponseEntity<LineGarage> addOneStation(@PathVariable long stationId, @RequestBody inGarageDTO inGarageDTO) throws NotFoundException {
        logger.info("Begin add garage by station Id");
        LineGarage newGarage = lineGarageService.addNewGarByLine(inGarageDTO, stationId);
        logger.info("Finish add garage by station Id");
        return ResponseEntity.status(HttpStatus.CREATED).body(newGarage);
    }
    @PutMapping("/garage/{id}")
    public ResponseEntity<LineGarage> modyGarage(@PathVariable long id, @RequestBody LineGarage lineGarage) throws NotFoundException {
        logger.info("Begin modify garage by Id");
        LineGarage changeGarage = lineGarageService.modGarage(id, lineGarage);
        logger.info("Finish modify garage by Id");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changeGarage);
    }
    @DeleteMapping("/garage/{garageId}")
    public ResponseEntity<LineGarage> delGarage(@PathVariable long garageId) throws NotFoundException {
        logger.info("Begin delete garage by garage Id");
        lineGarageService.deleteGarage(garageId);
        logger.info("Finish delete garage by garageId");
        return ResponseEntity.noContent().build();
    }

}
