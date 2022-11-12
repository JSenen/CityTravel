package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineTicket;
import com.juansenen.citytravel.domain.LineType;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineTicketService {
    List<LineTicket> findAll();
    Optional<LineTicket> findById(long id) throws LineNoFoundException;
     LineTicket addLin(LineTicket lineticket);
    void delLineTick (long id) throws LineNoFoundException;
    LineTicket modLineTyp(long id, LineTicket lineTicket) throws LineNoFoundException;
}
