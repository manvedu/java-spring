package org.example.facade;

import org.example.model.User;
import org.example.model.Event;
import org.example.model.Ticket;

public interface BookingFacade {
    User createUser(User user);
    Event createEvent(Event event);
    Ticket bookTicket(Ticket ticket);
}
