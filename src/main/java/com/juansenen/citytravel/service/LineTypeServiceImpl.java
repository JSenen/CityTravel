package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineTypeServiceImpl implements LineTypeService{

    @Autowired
    LineTypeRepository lineTypeRepository;
}
