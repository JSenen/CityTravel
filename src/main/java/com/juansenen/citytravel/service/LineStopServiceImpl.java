package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineStopServiceImpl implements LineStopService{

    @Autowired
    LineStopRepository lineStopRepository;
}
