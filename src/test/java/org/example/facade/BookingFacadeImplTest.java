
package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.model.UserAccount;
import org.example.service.UserService;
import org.example.service.EventService;
import org.example.service.TicketService;
import org.example.service.UserAccountService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
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

    @Mock
    private UserAccountService userAccountService;

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
        String email = "mjackson@testing.com";

        bookingFacade.createUser(userId, userName, email);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).saveUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(userId, capturedUser.getId());
        assertEquals(userName, capturedUser.getName());
        assertEquals(email, capturedUser.getEmail());
    }

    @Test
    public void testCreateEvent() {
        Long eventId = 1L;
        String title = "Michael Jackson";
        String description = "Just a normal concert";
        String date = " 2024-10-10";
        Double ticketPrice = 123.0;
        Event event = new Event(eventId, title, date, description, ticketPrice);

        bookingFacade.createEvent(eventId, title, date, description, ticketPrice);

        verify(eventService, times(1)).createEvent(eventId, title, date, description, ticketPrice);
    }

    @Test
    public void testBookTicket() {
        Long userId = 1L;
        Long eventId = 2L;
        int seatNumber = 123;

        UserAccount userAccount = new UserAccount(new User(userId, "Michael", "mjackson@testing.com"), 500.0);
        when(userAccountService.getAccount(userId)).thenReturn(userAccount);

        Event event = new Event(eventId, "Concert", "2024-10-10", "Music Event", 100.0);
        when(eventService.getEvent(eventId)).thenReturn(event);

        bookingFacade.bookTicket(userId, eventId, seatNumber);

        verify(eventService, times(1)).getEvent(eventId);
        verify(userAccountService, times(1)).getAccount(userId);
        verify(ticketService, times(1)).bookTicket(anyLong(), eq(eventId), eq(seatNumber));


    }
}
