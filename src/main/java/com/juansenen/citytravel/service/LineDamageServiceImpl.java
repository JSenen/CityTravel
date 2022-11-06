package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineDamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineDamageServiceImpl implements LineDamageService{

    @Autowired
    LineDamageRepository lineDamageRepository;

}
