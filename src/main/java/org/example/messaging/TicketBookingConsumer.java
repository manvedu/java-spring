package org.example.messaging;

import org.example.facade.BookingFacade;
import org.example.model.Ticket;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingConsumer {

    private final BookingFacade bookingFacade;

    public TicketBookingConsumer(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @JmsListener(destination = "ticket.booking.queue")
    public void processTicketBooking(Ticket ticket) {
        System.out.println("Received ticket booking message: " + ticket);
        bookingFacade.bookTicket(ticket.getId(), ticket.getEventId(), ticket.getUserId(), ticket.getSeatNumber());
        System.out.println("Ticket booking processed successfully.");
    }
}
