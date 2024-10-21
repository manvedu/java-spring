package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;

public interface BookingFacade {
    void createUser(Long id, String name, String email);

    void refillAccount(Long userId, double amount);

    void bookTicket(Long userId, Long eventId, int seatNumber);

    Event getEvent(Long eventId);

    Event createEvent(Long id, String title, String description, String date, Double ticketPrice);
}