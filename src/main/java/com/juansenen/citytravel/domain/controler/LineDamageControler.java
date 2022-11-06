package com.juansenen.citytravel.controler;

import com.juansenen.citytravel.service.LineDamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineDamageControler {

    @Autowired
    LineDamageService lineDamageService;
}
