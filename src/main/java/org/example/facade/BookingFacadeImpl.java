package com.example.facade;

import com.example.model.Event;
import com.example.model.Ticket;
import com.example.model.UserAccount;
import com.example.service.UserAccountService;
import com.example.service.EventService;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Override
    public void refillAccount(Long userId, double amount) {
        userAccountService.refillAccount(userId, amount);
    }

    @Override
    @Transactional
    public void bookTicket(Long userId, Long eventId, int seatNumber) {
        Event event = eventService.getEvent(eventId);
        UserAccount account = userAccountService.getAccount(userId);

        if (account.getBalance() >= event.getTicketPrice()) {
            account.setBalance(account.getBalance() - event.getTicketPrice());
            userAccountService.save(account);
            Ticket ticket = new Ticket(userId, eventId, seatNumber);
            ticketService.bookTicket(ticket);
        } else {
            throw new InsufficientFundsException("Not enough money.");
        }
    }

    @Override
    public Event getEvent(Long eventId) {
        return eventService.getEvent(eventId);
    }
}