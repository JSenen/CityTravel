package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineType;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineTypeServiceImpl implements LineTypeService{

    @Autowired
    LineTypeRepository lineTypeRepository;

    @Override
    public List<LineType> findAll() {
        return lineTypeRepository.findAll();
    }

    @Override
    public Optional<LineType> findById(long id) {
        return lineTypeRepository.findById(id);
    }

    @Override
    public LineType add(LineType linetype) {
        return lineTypeRepository.save(linetype);
    }

    @Override
    public void delLineTyp(long id) throws LineNoFoundException {
        LineType delline = findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineTypeRepository.deleteById(id);

    }
    @Override
    public LineType modLineTyp(long id, LineType linetype) throws LineNoFoundException {
        LineType linemod = findById(id)
                .orElseThrow(LineNoFoundException::new);
        linemod.setType(linetype.getType());
        linemod.setVolume(linetype.getVolume());
        return lineTypeRepository.save(linetype);
    }

}
