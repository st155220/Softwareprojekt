package com.skillball.repository;

import com.skillball.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findByTicketId(Integer ticketId);
}