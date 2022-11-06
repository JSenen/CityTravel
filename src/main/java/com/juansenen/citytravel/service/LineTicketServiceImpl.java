package com.juansenen.citytravel.service;

import com.juansenen.citytravel.repository.LineTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineTicketServiceImpl implements LineTicketService{

    @Autowired
    LineTicketRepository lineTicketRepository;
}
