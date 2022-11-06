package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineRepository lineRepository;
}
