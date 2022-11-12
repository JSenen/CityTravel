package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineStop;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LineStopServiceImpl implements LineStopService{

    @Autowired
    LineStopRepository lineStopRepository;

    @Override
    public List<LineStop> findAll() {
        return lineStopRepository.findAll();
    }

    @Override
    public LineStop findById(long id) throws LineNoFoundException {
        return lineStopRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
    }

    @Override
    public LineStop addLinStop(LineStop linestop) {
        return lineStopRepository.save(linestop);
    }
    @Override
    public void dellLinStop(long id) throws LineNoFoundException {
        LineStop delLineS = findById(id);
        lineStopRepository.deleteById(id);
    }

    @Override
    public LineStop modLinStop(long id, LineStop linestop) throws LineNoFoundException {
        LineStop linmod = findById(id);
        linmod.setLatitude(linestop.getLatitude());
        linmod.setLongitude(linestop.getLongitude());

        return lineStopRepository.save(linmod);
    }
}
