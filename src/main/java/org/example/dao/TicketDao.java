package org.example.dao;

import org.example.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao extends CrudRepository<Ticket, Long> {
}