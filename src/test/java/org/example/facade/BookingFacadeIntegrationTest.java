package org.example.facade;


import org.example.model.User;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.service.*;
import org.example.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BookingFacadeIntegrationTest {

    //private BookingFacadeImpl bookingFacade;


    @Test
    public void testBookingFacade() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookingFacade bookingFacade = context.getBean("bookingFacade", BookingFacade.class);
        UserService userService = context.getBean("userService", UserService.class);
        EventService eventService = context.getBean("eventService", EventService.class);
        TicketService ticketService = context.getBean("ticketService", TicketService.class);

        Long userId = 1L;
        String userName = "testUser";
        User user = new User(userId, userName, "john@example.com");
        bookingFacade.createUser(user);

        User createdUser = userService.getUser(userId) ;
        assertEquals(userName, createdUser.getName());

        Long eventId = 1L;
        Event event = new Event(eventId, "Concert", "It' a festival", "2014-10-10");
        bookingFacade.createEvent(event);

        Event createdEvent = eventService.getEvent(event.getId());
        assertEquals(eventId, createdEvent.getId());


        Long ticketId = 7L;
        Ticket ticket = new Ticket(ticketId, user.getId(), event.getId(), 333);
        bookingFacade.bookTicket(ticket);

        Ticket bookedTicket = ticketService.getTicket(ticket.getId());
        assertEquals(ticketId, bookedTicket.getId());
    }
}
