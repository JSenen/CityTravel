package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineGarageService {

    List<LineGarage> findAll();
    Optional<LineGarage> findById(long id);
    LineGarage findByCode(String code);
    LineGarage delGarage (long id) throws LineNoFoundException;
    LineGarage modGarage (long id, LineGarage lineGarage) throws LineNoFoundException;
    LineGarage addGarage (LineGarage lineGarage) throws StationNoFoundException;
}
