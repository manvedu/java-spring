package org.example.service;

import org.example.dao.EventDao;
import org.example.model.Event;

import org.springframework.beans.factory.annotation.Autowired;

public class EventService {
    private EventDao eventDao;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    public Event getEvent(Long id) {
        return eventDao.getById(id);
    }
}
