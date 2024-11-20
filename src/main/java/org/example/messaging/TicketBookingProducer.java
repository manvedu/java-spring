package org.example.messaging;

import org.example.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessagingTest {

    @Autowired
    private TicketBookingProducer producer;

    @Test
    public void testTicketBookingMessage() {
        Ticket ticket = new Ticket(1L, 101L, 202L, 15); // Example ticket
        producer.sendTicketBookingMessage(ticket);
        System.out.println("Test message sent.");
    }
}
