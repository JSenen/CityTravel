package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.dto.outGarageDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineGarageService {

    List<outGarageDTO> findAll();
    Optional<LineGarage> findById(long id);
    LineGarage modGarage (long id, LineGarage lineGarage) throws LineNoFoundException;
    LineGarage addGarage (LineGarage lineGarage) throws StationNoFoundException;

    LineGarage addNewGarByLine(LineGarage lineGarage, long stationId) throws StationNoFoundException;

    List<outGarageDTO> searchByTallerOrRecHumOrPaintService(boolean mechanic, boolean rrhh, boolean pService);

    LineGarage deleteGarage(long garageId) throws LineNoFoundException;
}
