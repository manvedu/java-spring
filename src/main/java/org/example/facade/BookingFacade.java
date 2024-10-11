package com.example.facade;

import com.example.booking.model.Event;
import com.example.booking.model.Ticket;

public interface BookingFacade {
    void createUser(Long id, String name, String email);

    void refillAccount(Long userId, double amount);

    void bookTicket(Long userId, Long eventId, int seatNumber);

    Event getEvent(Long eventId);
}