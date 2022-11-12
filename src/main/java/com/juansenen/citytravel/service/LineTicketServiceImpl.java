package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineTicket;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineTicketServiceImpl implements LineTicketService{

    @Autowired
    LineTicketRepository lineTicketRepository;

    @Override
    public List<LineTicket> findAll() {
        return lineTicketRepository.findAll();
    }

    @Override
    public Optional<LineTicket> findById(long id) throws LineNoFoundException {
        return Optional.ofNullable(lineTicketRepository.findById(id)
                .orElseThrow(LineNoFoundException::new));
    }

    @Override
    public LineTicket addLin(LineTicket lineticket) {
        return lineTicketRepository.save(lineticket);
    }

    @Override
    public void delLineTick(long id) throws LineNoFoundException {
        LineTicket lineTicket = findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineTicketRepository.deleteById(id);
    }

    @Override
    public LineTicket modLineTyp(long id, LineTicket lineTicket) throws LineNoFoundException {
        LineTicket linemod = findById(id)
                .orElseThrow(LineNoFoundException::new);
        linemod.setCode(lineTicket.getCode());
        linemod.setPrice(lineTicket.getPrice());
        linemod.setTravelnumber(lineTicket.getTravelnumber());

        return lineTicketRepository.save(linemod);
    }
}
