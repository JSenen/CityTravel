package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineTicketRepository extends CrudRepository<LineTicket, Long> {
}
