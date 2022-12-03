package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.*;
import com.juansenen.citytravel.domain.dto.inAccessDTO;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import com.juansenen.citytravel.exception.NotFoundException;
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
    public LineAccess addNewAccessByStation(inAccessDTO inAccessDTO, long stationid) throws NotFoundException {
        LineAccess newAccess = new LineAccess();
        modelMapper.map(inAccessDTO,newAccess);
        LineStation lineStation = lineStationRepository.findById(stationid)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        newAccess.setLineStationAccess(lineStation);
        return lineAccessRepository.save(newAccess);
    }
    public LineAccess delAccess(long id) throws NotFoundException {
        LineAccess delAccess = lineAccessRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        lineAccessRepository.deleteById(id);
        return delAccess;
    }

    @Override
    public LineAccess modyAccess(long id, LineAccess lineAccess) throws NotFoundException {
        LineAccess modAccess = lineAccessRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        modAccess.setStreet(lineAccess.getStreet());
        modAccess.setNum(lineAccess.getNum());
        modAccess.setLatitude(lineAccess.getLatitude());
        modAccess.setLongitude(lineAccess.getLongitude());
        modAccess.setElevator(lineAccess.isElevator());

        return lineAccessRepository.save(modAccess);
    }
}
