package org.example.service;

import org.example.dao.TicketDao;
import org.example.model.Ticket;

public class TicketService {
    private TicketDao ticketDao;

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


