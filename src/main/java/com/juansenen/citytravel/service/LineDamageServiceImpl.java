package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineDamage;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineDamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineDamageServiceImpl implements LineDamageService{

    @Autowired
    LineDamageRepository lineDamageRepository;

    @Override
    public List<LineDamage> findAll() {
        return lineDamageRepository.findAll();
    }

    @Override
    public LineDamage findById(long id) throws LineNoFoundException {
        return lineDamageRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
    }
    @Override
    public LineDamage add(LineDamage lineDamage) {
        return lineDamageRepository.save(lineDamage);
    }

    @Override
    public void delDamage(long id) throws LineNoFoundException {
        LineDamage dellineDam = findById(id);
        lineDamageRepository.deleteById(id);
    }
    @Override
    public LineDamage modDamage(long id, LineDamage lineDamage) throws LineNoFoundException {
        LineDamage modLine = findById(id);
        modLine.setDatestart(lineDamage.getDatestart());
        modLine.setDatefinish(lineDamage.getDatefinish());

        return lineDamageRepository.save(lineDamage);

    }
}
