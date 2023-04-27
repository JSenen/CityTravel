package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.dto.inAccessDTO;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface LineAccessService {

    List<outAccessDTO> findAll();
    Optional<LineAccess> findById(long id);
    LineAccess delAccess(long id) throws NotFoundException;
    LineAccess modyAccess(long id, LineAccess lineAccess) throws NotFoundException;
    LineAccess addNewAccessByStation(LineAccess lineAccess, long stationid) throws NotFoundException;

    List<outAccessDTO> searchAccessWithElevator(boolean elevator);

    LineAccess updateOneAccess(long stationid, LineAccess lineAccess) throws NotFoundException;
}
