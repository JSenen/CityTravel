package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inStationDTO;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineStationService {

    List<LineStation> findAll();
    Optional<LineStation> findById(long id) throws StationNoFoundException;
    LineStation delStation (long id) throws NotFoundException;
    LineStation modStation (long id, LineStation lineStation) throws NotFoundException;
    LineStation addStation (long lineId, LineStation lineStation) throws NotFoundException;
    List<LineStation> findAllStationWithWifiBusAndTaxi(boolean wifi, boolean busStation, boolean taxiStation);


    LineStation updateOneStation(long lineId, LineStation lineStation) throws NotFoundException;


    List<LineStation> findByLineId(long lineId);
}

