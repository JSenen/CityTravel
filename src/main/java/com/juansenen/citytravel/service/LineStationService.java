package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineStationService {

    List<LineStation> findAll();
    Optional<LineStation> findById(long id) throws StationNoFoundException;
    LineStation delStation (long id) throws LineNoFoundException;
    LineStation modStation (long id, LineStation lineStation) throws LineNoFoundException;
    LineStation addStation (LineStation lineStation);
}
