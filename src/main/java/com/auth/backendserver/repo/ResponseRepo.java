package com.auth.backendserver.repo;

import com.auth.backendserver.model.Responses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponseRepo extends JpaRepository<Responses, Integer> {
    Optional<List<Responses>> findByTicket_TicketId(UUID ticketId);
}
