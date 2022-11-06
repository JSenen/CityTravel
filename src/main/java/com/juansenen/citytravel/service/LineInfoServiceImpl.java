package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineInfoServiceImpl implements LineInfoService{

    @Autowired
    LineInfoRepository lineInfoRepository;
}
