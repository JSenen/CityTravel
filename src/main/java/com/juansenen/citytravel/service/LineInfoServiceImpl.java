package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineInfo;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineInfoServiceImpl implements LineInfoService{

    @Autowired
    LineInfoRepository lineInfoRepository;

    @Override
    public List<LineInfo> findAll() {
        return lineInfoRepository.findAll();
    }

    @Override
    public LineInfo findById(long id) throws LineNoFoundException {
        return lineInfoRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
    }
    @Override
    public LineInfo add(LineInfo lineinfo)  {
        return lineInfoRepository.save(lineinfo);
    }

    @Override
    public void delLinInfo(long id) throws LineNoFoundException{
        LineInfo delline = findById(id);
        lineInfoRepository.deleteById(id);
    }

    @Override
    public LineInfo lineInfmod(long id, LineInfo lineinfo) throws LineNoFoundException {
        LineInfo modline = findById(id);

        modline.setCode(lineinfo.getCode());
        modline.setMail(lineinfo.getMail());
        modline.setPhone(lineinfo.getPhone());
        modline.setSaletickets(lineinfo.isSaletickets());
        modline.setStreet(lineinfo.getStreet());

        return lineInfoRepository.save(modline);
    }
}
