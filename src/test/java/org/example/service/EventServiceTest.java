package org.example.service;

import org.example.dao.EventDao;
import org.example.model.Event;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.*;

public class EventServiceTest {

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventService eventService;
    private Event mockedEvent;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedEvent = new Event(1L, "Michael Concert", " 2024-10-10", "Just a normal concert", 12.3 );
    }

    @Test
    public void testCreateEvent() {
        Long id = 1L;
        String title = "Michael Concert";
        String description = "Just a normal concert";
        String date = " 2024-10-10";
        Double ticketPrice = 123.4;

        eventService.createEvent(id, title, description, date, ticketPrice);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventDao, times(1)).save(eventCaptor.capture());

        Event capturedEvent = eventCaptor.getValue();

        assertEquals(id, capturedEvent.getId());
    }

    @Test
    public void testGetEvent() {
        String title = "Michael Jackson";
        Long eventId = 1L;
        Event event = new Event(eventId, "Michael Concert", " 2024-10-10" , "Just a normal concert",12.3);
        when(eventDao.findById(eventId)).thenReturn(Optional.of(event));


        Event previousEvent = eventService.getEvent(eventId);

        assertEquals(eventId, previousEvent.getId());
        verify(eventDao, times(1)).findById(eventId);
    }
}
