package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineGarageRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineGarageServiceImpl implements LineGarageService{

    @Autowired
    LineGarageRepository lineGarageRepository;
    @Autowired
    LineStationRepository lineStationRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<LineGarage> findAll() {
        return lineGarageRepository.findAll();
    }

    @Override
    public Optional<LineGarage> findById(long id) {
        return lineGarageRepository.findById(id);
    }

    @Override
    public List<LineGarage> searchByTallerOrRecHumOrPaintService(boolean mechanic, boolean rrhh, boolean pService) {
        return lineGarageRepository.findAllGarageWithTallerOrRrhhOrPaintService(mechanic, rrhh, pService);
    }

    @Override
    public LineGarage addGarage(LineGarage lineGarage) throws StationNoFoundException {
        LineGarage newGarage = lineGarageRepository.save(lineGarage);
        return newGarage;

    }

    @Override
    public LineGarage addNewGarByLine(LineGarage lineGarage, long stationId) throws StationNoFoundException {

        LineGarage newGarage = new LineGarage();

        modelMapper.map(lineGarage, newGarage);

        LineStation lineStation = lineStationRepository.findById(stationId)
                .orElseThrow(StationNoFoundException::new);

        newGarage.setLineStationGarage(lineStation);
        return lineGarageRepository.save(newGarage);
    }

    @Override
    public LineGarage modGarage(long id, LineGarage lineGarage) throws LineNoFoundException {
        LineGarage modGarage = lineGarageRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        modGarage.setCodeGarage(lineGarage.getCodeGarage());
        modGarage.setPaintService(lineGarage.isPaintService());
        modGarage.setRrhh(lineGarage.isRrhh());
        modGarage.setSurface(lineGarage.getSurface());
        modGarage.setTaller(lineGarage.isTaller());
        //modGarage.setLineTrainList(lineGarage.getLineTrainList());

        return lineGarageRepository.save(modGarage);
    }

    @Override
    public LineGarage deleteGarage(long garageId) throws LineNoFoundException {
        LineGarage garage = lineGarageRepository.findById(garageId)
                .orElseThrow(LineNoFoundException::new);
        lineGarageRepository.deleteById(garageId);
        return garage;
    }
}
