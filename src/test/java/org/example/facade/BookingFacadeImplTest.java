
package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.EventService;
import org.example.service.TicketService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BookingFacadeImplTest {
    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {

        Long userId = 1L;
        String userName = "Michael Jackson";

        User user = new User(userId, userName, "mjackson@testing.com");
        when(userService.createUser(user)).thenReturn(user);
        User createdUser = bookingFacade.createUser(user);

        verify(userService, times(1)).createUser(user);
        assertEquals(userName, createdUser.getName());

    }

    @Test
    public void testCreateEvent() {

        String title = "Michael Jackson";
        Long eventId = 1L;
        Event event = new Event(eventId, title, "Just a normal concert"," 2024-10-10" );
        when(eventService.createEvent(event)).thenReturn(event);
        Event createdEvent = bookingFacade.createEvent(event);

        verify(eventService, times(1)).createEvent(event);
        assertEquals(eventId, createdEvent.getId());
    }

    @Test
    public void testBookTicket() {

        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, 1L, 1L, 123);

        when(ticketService.bookTicket(ticket)).thenReturn(ticket);
        Ticket createdTicket = bookingFacade.bookTicket(ticket);

        verify(ticketService, times(1)).bookTicket(ticket);
        assertEquals(ticketId, createdTicket.getId());
    }
}
