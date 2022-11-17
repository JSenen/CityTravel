package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
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

    @Override
    public LineGarage addGarage(LineGarage lineGarage) throws StationNoFoundException {
        LineGarage newGarage = lineGarageRepository.save(lineGarage);
        return newGarage;

    }

    @Override
    public LineGarage findByCode(String code) {
        return lineGarageRepository.findByCode(code);
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
