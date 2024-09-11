package org.example.service;

import org.example.dao.TicketDao;
import org.example.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;

public class TicketService {
    private TicketDao ticketDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket bookTicket(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketDao.getById(id);
    }
}


