package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.service.LineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineInfoControler {

    @Autowired
    LineInfoService lineInfoService;
}
