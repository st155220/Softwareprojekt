package com.skillball.service;

import com.skillball.entity.Ticket;
import com.skillball.entity.User;
import com.skillball.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    public List<Ticket> findAllTicket() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketByTicketId(Integer ticketId) {
        return ticketRepository.findByTicketId(ticketId);
    }

    public List<Ticket> listRelevantTickets(User user) {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : findAllTicket()) {
            if (ticket.getRequester() == user && !ticket.isDeletedByUser()) {
                tickets.add(ticket);
            }
        }
        tickets.sort(new TicketComparator());
        return tickets;
    }

    public List<Ticket> listRelevantTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : findAllTicket()) {
            if (!ticket.isDeletedByAdmin()) {
                tickets.add(ticket);
            }
        }
        tickets.sort(new TicketComparator());
        return tickets;
    }

    private class TicketComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket ticket1, Ticket ticket2) {
            return ticket2.getTimeStamp().compareTo(ticket1.getTimeStamp());
        }
    }
}