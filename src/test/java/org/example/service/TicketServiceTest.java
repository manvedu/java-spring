package org.example.service;


import org.example.dao.TicketDao;
import org.example.model.Ticket;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private  TicketService ticketService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testbookTicket() {
        Ticket ticket = new Ticket(1L, 1L, 1L, 123);
        ticketService.bookTicket(ticket);
        verify(ticketDao, times(1)).save(ticket);
    }

    @Test
    public void testgetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, 1L, 1L, 123);
        when(ticketDao.getById(ticketId)).thenReturn(ticket);

        Ticket createdTicket = ticketService.getTicket(ticketId);

        assertEquals(ticketId, createdTicket.getId());
        verify(ticketDao, times(1)).getById(ticketId);
    }
}