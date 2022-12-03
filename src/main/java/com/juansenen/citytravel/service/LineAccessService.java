package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.dto.inAccessDTO;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineAccessService {

    List<outAccessDTO> findAll();
    Optional<LineAccess> findById(long id);
    LineAccess delAccess(long id) throws LineNoFoundException;
    LineAccess modyAccess(long id, LineAccess lineAccess) throws LineNoFoundException;
    LineAccess addNewAccessByStation(inAccessDTO inAccessDTO, long stationid) throws StationNoFoundException;

    List<outAccessDTO> searchAccessWithElevator(boolean elevator);
}
