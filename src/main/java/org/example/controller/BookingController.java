package org.example.controller;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingFacade bookingFacade;

    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    // User Resource Endpoints
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = bookingFacade.createUser(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = bookingFacade.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam(required = false) String name) {
        List<User> users = (name != null) ? bookingFacade.getUsersByName(name) : bookingFacade.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Event Resource Endpoints
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = bookingFacade.createEvent(event.getId(), event.getTitle(), event.getDescription(), event.getDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(bookingFacade.getAllEvents());
    }

    // Ticket Resource Endpoints
    @PostMapping("/tickets")
    public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket) {
        Ticket bookedTicket = bookingFacade.bookTicket(ticket.getId(), ticket.getEventId(), ticket.getUserId(), ticket.getSeatNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookedTicket);
    }

    @PostMapping("/tickets/batch")
    public ResponseEntity<String> preloadTickets(@RequestParam String filePath) {
        try {
            bookingFacade.preloadTickets(filePath);
            return ResponseEntity.ok("Batch ticket creation successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to preload tickets: " + e.getMessage());
        }
    }
/*
    @GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void getBookedTicketsPdf(
            @RequestParam Long userId,
            @RequestParam int pageSize,
            @RequestParam int pageNum,
            HttpServletResponse response) throws IOException, DocumentException {

        User user = bookingFacade.getUser(userId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Booked Tickets for User: " + user.getName()));
        document.add(new Paragraph("Email: " + user.getEmail()));
        document.add(new Paragraph(" "));

        for (Ticket ticket : tickets) {
            document.add(new Paragraph("Ticket ID: " + ticket.getId() +
                    ", Event ID: " + ticket.getEventId() +
                    ", Seat Number: " + ticket.getSeatNumber()));
        }

        document.close();
    }

 */
}
