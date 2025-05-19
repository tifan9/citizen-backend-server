package com.auth.backendserver.service;

import com.auth.backendserver.dto.TicketRequest;
import com.auth.backendserver.model.Tickets;
import com.auth.backendserver.repo.CategoryRepo;
import com.auth.backendserver.repo.TicketRepo;
import com.auth.backendserver.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UsersRepo usersRepo;
    private final TicketRepo ticketRepo;

    @Autowired
    public TicketService(TicketRepo ticketRepo, CategoryRepo categoryRepo, UsersRepo usersRepo) {
        this.categoryRepo = categoryRepo;
        this.usersRepo = usersRepo;
        this.ticketRepo = ticketRepo;
    }

    // Create or update a ticket
    public void saveTicket(Tickets ticket) {
        if(ticket !=null){
            // Save the ticket
             ticketRepo.save(ticket);
        }
    }
    // Retrieve all tickets
    public List<Tickets> getAllTickets() {
        return ticketRepo.findAll();
    }

    // Retrieve a ticket by ID
    public Optional<Tickets> getTicketById(UUID ticketId) {
        return ticketRepo.findById(ticketId);
    }

    // Delete a ticket by ID
    public void deleteTicketById(UUID ticketId) {
        ticketRepo.deleteById(ticketId);
    }
}