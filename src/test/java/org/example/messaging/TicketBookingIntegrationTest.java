package org.example.messaging;

import org.example.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;

import javax.jms.ConnectionFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Ensures isolation between tests
public class TicketBookingIntegrationTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    public void testTicketBookingMessageSentAndReceived() {
        Ticket ticket = new Ticket(1L, 101L, 202L, 15);

        jmsTemplate.convertAndSend("ticket.booking.queue", ticket);

        Ticket receivedTicket = (Ticket) jmsTemplate.receiveAndConvert("ticket.booking.queue");

        assertThat(receivedTicket).isNotNull();
        assertThat(receivedTicket.getId()).isEqualTo(ticket.getId());
        assertThat(receivedTicket.getEventId()).isEqualTo(ticket.getEventId());
        assertThat(receivedTicket.getUserId()).isEqualTo(ticket.getUserId());
        assertThat(receivedTicket.getSeatNumber()).isEqualTo(ticket.getSeatNumber());
    }
}
