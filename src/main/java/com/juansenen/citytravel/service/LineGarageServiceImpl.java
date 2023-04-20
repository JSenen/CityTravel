package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.inGarageDTO;
import com.juansenen.citytravel.domain.dto.outGarageDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineGarageRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public List<outGarageDTO> findAll() {
        List<LineGarage> lineGarages = lineGarageRepository.findAll();
        List<outGarageDTO> outGarageDTOS = modelMapper.map(lineGarages, new TypeToken<List<outGarageDTO>>(){}.getType());
        return outGarageDTOS;
    }

    @Override
    public Optional<LineGarage> findById(long id) {
        return lineGarageRepository.findById(id);
    }

    @Override
    public List<outGarageDTO> searchByTallerOrRecHumOrPaintService(boolean mechanic, boolean rrhh, boolean pService) {
        List<LineGarage> garages = lineGarageRepository.findAllGarageWithTallerOrRrhhOrPaintService(mechanic,rrhh,pService);
        List<outGarageDTO> garageDTOS = modelMapper.map(garages, new TypeToken<List<outGarageDTO>>(){}.getType());
        return garageDTOS;
    }

    @Override
    public LineGarage addNewGarByLine(LineGarage lineGarage, long stationId) throws NotFoundException {

        LineGarage newGarage = new LineGarage();

        modelMapper.map(lineGarage, newGarage);

        LineStation lineStation = lineStationRepository.findById(stationId)
                .orElseThrow(()-> new NotFoundException(new LineStation()));

        newGarage.setLineStationGarage(lineStation);
        return lineGarageRepository.save(newGarage);
    }

    @Override
    public LineGarage modGarage(long id, LineGarage lineGarage) throws NotFoundException {
        LineGarage modGarage = lineGarageRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new LineGarage()));
        modGarage.setCodeGarage(lineGarage.getCodeGarage());
        modGarage.setPaintService(lineGarage.isPaintService());
        modGarage.setRrhh(lineGarage.isRrhh());
        modGarage.setSurface(lineGarage.getSurface());
        modGarage.setTaller(lineGarage.isTaller());
        //modGarage.setLineTrainList(lineGarage.getLineTrainList());

        return lineGarageRepository.save(modGarage);
    }

    @Override
    public LineGarage deleteGarage(long garageId) throws NotFoundException {
        LineGarage garage = lineGarageRepository.findById(garageId)
                .orElseThrow(()-> new NotFoundException("Garage Not found exception"));
        lineGarageRepository.deleteById(garageId);
        return garage;
    }

    @Override
    public LineGarage updateGarg(long garageId, LineGarage lineGarage) throws NotFoundException {
        LineGarage updGarage = lineGarageRepository.findById(garageId)
                .orElseThrow(()-> new NotFoundException((new LineGarage())));
        updGarage.setPaintService(lineGarage.isPaintService());
        updGarage.setRrhh(lineGarage.isRrhh());
        updGarage.setTaller(lineGarage.isTaller());
        return lineGarageRepository.save(updGarage);
    }
}
