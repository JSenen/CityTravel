package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.service.LineStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineStopControler {

    @Autowired
    LineStopService lineStopService;
}
