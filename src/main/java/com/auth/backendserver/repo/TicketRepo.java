package com.auth.backendserver.repo;

import com.auth.backendserver.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepo extends JpaRepository<Tickets, UUID> {
    Optional <Tickets> findByTicketId(UUID ticketId);
}
