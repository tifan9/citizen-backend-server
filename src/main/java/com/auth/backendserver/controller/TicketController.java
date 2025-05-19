package com.auth.backendserver.controller;

import com.auth.backendserver.dto.TicketRequest;
import com.auth.backendserver.model.Categories;
import com.auth.backendserver.model.Tickets;
import com.auth.backendserver.model.Users;
import com.auth.backendserver.repo.UsersRepo;
import com.auth.backendserver.service.CategoryService;
import com.auth.backendserver.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final CategoryService categoryService;
    private final UsersRepo usersRepo;

    @Autowired
    public TicketController(TicketService ticketService, CategoryService categoryService, UsersRepo usersRepo) {
        this.ticketService = ticketService;
        this.categoryService = categoryService;
        this.usersRepo = usersRepo;
    }

    // Create or update a ticket
    @PostMapping("/create_ticket")
    public ResponseEntity<?> saveTicket(@RequestBody TicketRequest ticketRequest) {
        // Validate that categoryId is not null
        if (ticketRequest.getCategoryId() == null) {
            return ResponseEntity.badRequest().body("Category ID must not be null");
        }
        if (ticketRequest.getUserId() == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }

        // Validate the category ID
        Categories categories = categoryService.getCategoryById(ticketRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + ticketRequest.getCategoryId()));
        Users user = usersRepo.findById(ticketRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + ticketRequest.getUserId()));

        // Create a new ticket and set its fields
        Tickets ticket = new Tickets();
        ticket.setCategory(categories);
        ticket.setUser(user);
        ticket.setMessage(ticketRequest.getMessage());
        ticket.setUpdatedAt(java.time.LocalDateTime.now());

        // Save the ticket
        ticketService.saveTicket(ticket);
        return ResponseEntity.ok(ticket);
    }


    // Get all tickets
    @GetMapping
    public ResponseEntity<List<Tickets>> getAllTickets() {
        List<Tickets> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable UUID id) {
        Optional<Tickets> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete ticket by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }
}