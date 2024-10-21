package com.example.booking.facade;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.UserAccount;
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


}