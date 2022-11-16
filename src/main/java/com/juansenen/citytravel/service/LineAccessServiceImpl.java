package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineAccessServiceImpl implements LineAccessService{

    @Autowired
    LineAccessRepository lineAccessRepository;

    @Override
    public List<LineAccess> findAll() {
        return lineAccessRepository.findAll();
    }

    @Override
    public Optional<LineAccess> findById(long id) {
        return lineAccessRepository.findById(id);
    }

    @Override
    public LineAccess addAccess(LineAccess lineAccess) {
        LineAccess newAccess = lineAccessRepository.save(lineAccess);
        return newAccess;
    }

    @Override
    public void delAccess(long id) throws LineNoFoundException {
        LineAccess delAccess = lineAccessRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);

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
