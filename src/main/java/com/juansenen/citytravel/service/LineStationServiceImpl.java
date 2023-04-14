package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inStationDTO;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineStationServiceImpl implements LineStationService{

    @Autowired
    LineStationRepository lineStationRepository;
    @Autowired
    LineRepository lineRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<LineStation> findAll() {
        List<LineStation> stations = lineStationRepository.findAll();
        List<LineStation> lineStations = modelMapper.map(stations, new TypeToken<List<LineStation>>(){}.getType());
        return lineStations;
    }

    @Override
    public List<LineStation> findAllStationWithWifiBusAndTaxi(boolean wifi, boolean busStation, boolean taxiStation) {
        List<LineStation> stations = lineStationRepository.findByWifiOrBusStationOrTaxiStation(wifi, busStation, taxiStation);
        List<LineStation> lineStations = modelMapper.map(stations, new TypeToken<List<LineStation>>(){}.getType());
        return lineStations;
    }

    @Override
    public Optional<LineStation> findById(long id) throws StationNoFoundException {
        return lineStationRepository.findById(id);
    }

    @Override
    public LineStation delStation(long id) throws NotFoundException {
        LineStation delStation = lineStationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        lineStationRepository.deleteById(id);
        return delStation;
    }

    @Override
    public LineStation addStation(long lineId, LineStation lineStation) throws NotFoundException {
        LineStation newStation = new LineStation();

        modelMapper.map(lineStation, newStation);
        Line line = lineRepository.findById(lineId)
                .orElseThrow(()-> new NotFoundException(new Line()));
        newStation.setLine(line);


        return lineStationRepository.save(newStation);
    }
    @Override
    public LineStation modStation(long id, LineStation lineStation) throws NotFoundException {
        LineStation modStation = lineStationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        modStation.setName(lineStation.getName());
        modStation.setHopen(lineStation.getHopen());
        modStation.setHclose(lineStation.getHclose());
        modStation.setBusStation(lineStation.isBusStation());
        modStation.setPtoInfo(lineStation.isPtoInfo());
        modStation.setTaxiStation(lineStation.isTaxiStation());
        modStation.setWifi(lineStation.isWifi());
        modStation.setLatitude(lineStation.getLatitude());
        modStation.setLongitude(lineStation.getLongitude());

        return lineStationRepository.save(modStation);
    }

    @Override
    public LineStation updateOneStation(long lineId, LineStation lineStation) throws NotFoundException {
        LineStation updStation = lineStationRepository.findById(lineId)
                .orElseThrow(()-> new NotFoundException(new LineStation()));
        updStation.setHopen(lineStation.getHopen());
        updStation.setHclose(lineStation.getHclose());

        return lineStationRepository.save(updStation);
    }

    @Override
    public List<LineStation> findByLineId(long lineId) {
        return lineStationRepository.findAllStationsByLineId(lineId);
    }
}
