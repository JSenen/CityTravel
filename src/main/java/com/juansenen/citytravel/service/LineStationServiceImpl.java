package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineStationServiceImpl implements LineStationService{

    @Autowired
    LineStationRepository lineStationRepository;

    @Override
    public List<LineStation> findAll() {
        return lineStationRepository.findAll();
    }

    @Override
    public Optional<LineStation> findById(long id) throws StationNoFoundException {
        return lineStationRepository.findById(id);
    }

    @Override
    public LineStation delStation(long id) throws LineNoFoundException {
        LineStation delStation = lineStationRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineStationRepository.deleteById(id);
        return delStation;
    }

    @Override
    public LineStation addStation(LineStation lineStation) {
        LineStation newStation = lineStationRepository.save(lineStation);
        return newStation;
    }

    @Override
    public LineStation modStation(long id, LineStation lineStation) throws LineNoFoundException {
        LineStation modStation = lineStationRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        modStation.setName(lineStation.getName());
        modStation.setHopen(lineStation.getHopen());
        modStation.setHclose(lineStation.getHclose());
        modStation.setBusSation(lineStation.isBusSation());
        modStation.setPtoInfo(lineStation.isPtoInfo());
        modStation.setTaxiStation(lineStation.isTaxiStation());
        modStation.setWifi(lineStation.isWifi());

        return lineStationRepository.save(modStation);
    }
}
