package org.example.service;

import org.example.dao.EventDao;
import org.example.model.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private EventDao eventDao;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event createEvent(Long id, String title, String description, String date, Double ticketPrice) {
        Event event = new Event(id, title, date, description, ticketPrice );
        logger.info("EventService - createEvent: " + event);
        return eventDao.save(event);
    }

    public Event getEvent(Long id) {
        logger.info("EventService - getEvent: " + id);
        Optional<Event> optionalEvent = eventDao.findById(id);
        return optionalEvent.orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }
}
