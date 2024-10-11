package com.example.booking.facade;

import com.example.booking.model.Event;
import com.example.booking.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

public class BookingFacadeIntegrationTest {

	@Autowired
	private BookingFacade bookingFacade;

	@Before
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookingFacade = context.getBean("bookingFacade", BookingFacade.class);
	}

	@Test
	public void testRefillAccountAndBooking() {
		bookingFacade.refillAccount(1L, 100.00);

		Event event = new Event();
		event.setTitle("Concert");
		event.setDate("2023-12-12");
		event.setTicketPrice(50.00);
		bookingFacade.createEvent(event);

		bookingFacade.bookTicket(1L, event.getId(), 1);

		UserAccount account = bookingFacade.getAccount(1L);
		assertEquals(50.00, account.getBalance(), 0.01);
	}
}