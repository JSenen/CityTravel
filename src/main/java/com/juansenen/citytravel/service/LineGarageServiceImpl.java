package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.LineGarageDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineGarageRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
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
    @Override
    public List<LineGarage> findAll() {
        return lineGarageRepository.findAll();
    }

    @Override
    public Optional<LineGarage> findById(long id) {
        return lineGarageRepository.findById(id);
    }

    @Override
    public LineGarage delGarage(long id) throws LineNoFoundException {
        LineGarage delGarage = lineGarageRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineGarageRepository.deleteById(id);
        return delGarage;
    }

    @Override //TODO Cambiar excepcion a StationNoFound (Crearla)
    public LineGarage addGarage(LineGarageDTO lineGarageDTO) throws LineNoFoundException {
        LineGarage newGarage = new LineGarage();
        newGarage.setCodeGarage(lineGarageDTO.getCodeGarage());
        newGarage.setRrhh(lineGarageDTO.isRrhh());
        newGarage.setPaintService(lineGarageDTO.isPaintService());
        newGarage.setSurface(lineGarageDTO.getSurface());
        newGarage.setTaller(lineGarageDTO.isTaller());

        LineStation station = lineStationRepository.findById(lineGarageDTO.getStationId())
                .orElseThrow(LineNoFoundException::new);
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
        modGarage.setLineStation(lineGarage.getLineStation());
        modGarage.setLineTrainList(lineGarage.getLineTrainList());

        return lineGarageRepository.save(modGarage);
    }
}
