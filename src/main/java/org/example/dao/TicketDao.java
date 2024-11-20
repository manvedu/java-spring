package org.example.dao;

import org.example.model.Ticket;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
    private final Map<Long, Ticket> ticketStorage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(idGenerator.getAndIncrement());
        }
        ticketStorage.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket getById(Long id) {
        return ticketStorage.get(id);
    }

    public Map<Long, Ticket> getAll() {
        return ticketStorage;
    }

    public List<Ticket> findTicketsByUser(User user, int pageSize, int pageNum) {
        List<Ticket> ticketsForUser = ticketStorage.values().stream()
                .filter(ticket -> ticket.getUserId().equals(user.getId()))
                .collect(Collectors.toList());

        int fromIndex = Math.min(pageSize * pageNum, ticketsForUser.size());
        int toIndex = Math.min(fromIndex + pageSize, ticketsForUser.size());

        return ticketsForUser.subList(fromIndex, toIndex);
    }
}
