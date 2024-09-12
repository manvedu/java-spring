package org.example.service;

import org.example.dao.EventDao;
import org.example.dao.UserDao;
import org.example.model.Event;

import org.example.model.User;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EventServiceTest {

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private  EventService eventService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent() {
        Event event = new Event(1L, "Michael Concert", "Just a normal concert"," 2024-10-10" );
        eventService.createEvent(event);
        verify(eventDao, times(1)).save(event);
    }

    @Test
    public void testGetEvent() {
        String title = "Michael Jackson";
        Long eventId = 1L;
        Event event = new Event(eventId, "Michael Concert", "Just a normal concert"," 2024-10-10" );
        when(eventDao.getById(eventId)).thenReturn(event);

        Event previousEvent = eventService.getEvent(eventId);

        assertEquals(eventId, previousEvent.getId());
        verify(eventDao, times(1)).getById(eventId);
    }
}