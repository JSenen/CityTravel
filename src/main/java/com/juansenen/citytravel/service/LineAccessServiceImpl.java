package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.*;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.domain.dto.outLineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineAccessRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineAccessServiceImpl implements LineAccessService{

    @Autowired
    LineAccessRepository lineAccessRepository;
    @Autowired
    LineStationRepository lineStationRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<outAccessDTO> findAll() {
        List<LineAccess> access = lineAccessRepository.findAll();
        List<outAccessDTO> accessDTO = modelMapper.map(access, new TypeToken<List<outAccessDTO>>() {}.getType());
        return accessDTO;
    }

    @Override
    public Optional<LineAccess> findById(long id) {
        return lineAccessRepository.findById(id);
    }

    @Override
    public List<outAccessDTO> searchAccessWithElevator(boolean elevator) {
        List<LineAccess> lineAccesses = lineAccessRepository.findByElevator(elevator);
        List<outAccessDTO> outAccessDTOS = modelMapper.map(lineAccesses, new TypeToken<List<outAccessDTO>>(){}.getType());
        return outAccessDTOS;
    }

    @Override
    public LineAccess addNewAccessByStation(LineAccess lineAccess, long stationid) throws StationNoFoundException {
        LineAccess newAccess = new LineAccess();
        modelMapper.map(lineAccess,newAccess);
        LineStation lineStation = lineStationRepository.findById(stationid)
                .orElseThrow(StationNoFoundException::new);
        return lineAccessRepository.save(newAccess);
    }
    public void  delAccess(long id) throws LineNoFoundException {
        LineAccess delAccess = lineAccessRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineAccessRepository.deleteById(id);
    }

    @Override
    public LineAccess modyAccess(long id, LineAccess lineAccess) throws LineNoFoundException {
        LineAccess modAccess = lineAccessRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        modAccess.setStreet(lineAccess.getStreet());
        modAccess.setNum(lineAccess.getNum());
        modAccess.setLatitude(lineAccess.getLatitude());
        modAccess.setLongitude(lineAccess.getLongitude());
        modAccess.setElevator(lineAccess.isElevator());

        return lineAccessRepository.save(modAccess);
    }
}
