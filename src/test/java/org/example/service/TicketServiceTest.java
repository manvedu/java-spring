package org.example.service;


import org.example.dao.TicketDao;
import org.example.model.Ticket;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.*;

public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;
    private Ticket mockedTicket;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedTicket = new Ticket(1L, 1L, 123);
    }

    @Test
    public void testbookTicket() {
        Long id = 1L;

        ticketService.bookTicket(id, 1L, 123);  // Pass null as the id
        ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketDao, times(1)).save(ticketCaptor.capture());

        Ticket capturedTicket = ticketCaptor.getValue();

        assertEquals(Long.valueOf(1L), capturedTicket.getUserId());
        assertEquals(Long.valueOf(1L), capturedTicket.getEventId());
        assertEquals(123, capturedTicket.getSeatNumber());
    }

    @Test
    public void testgetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, 1L, 123);
        ticket.setId(ticketId);
        when(ticketDao.findById(ticketId)).thenReturn(Optional.of(ticket));

        Ticket createdTicket = ticketService.getTicket(ticketId);

        assertEquals(ticketId, createdTicket.getId());
        verify(ticketDao, times(1)).findById(ticketId);
    }
}
