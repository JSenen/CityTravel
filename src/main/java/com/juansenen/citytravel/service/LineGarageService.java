package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.dto.inGarageDTO;
import com.juansenen.citytravel.domain.dto.outGarageDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineGarageService {

    List<outGarageDTO> findAll();
    Optional<LineGarage> findById(long id);
    LineGarage modGarage (long id, LineGarage lineGarage) throws NotFoundException;

    LineGarage addNewGarByLine(inGarageDTO inGarageDTO, long stationId) throws NotFoundException;

    List<outGarageDTO> searchByTallerOrRecHumOrPaintService(boolean mechanic, boolean rrhh, boolean pService);

    LineGarage deleteGarage(long garageId) throws NotFoundException;

    LineGarage updateGarg(long stationid, LineGarage lineGarage) throws NotFoundException;
}
