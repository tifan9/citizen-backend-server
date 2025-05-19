package com.auth.backendserver.controller;

import com.auth.backendserver.dto.ResponseRequest;
import com.auth.backendserver.model.Responses;
import com.auth.backendserver.model.Tickets;
import com.auth.backendserver.model.Users;
import com.auth.backendserver.repo.UsersRepo;
import com.auth.backendserver.service.CategoryService;
import com.auth.backendserver.service.ResponseService;
import com.auth.backendserver.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    private final ResponseService responseService;
    private final TicketService ticketService;
    private final UsersRepo usersRepo;
    @Autowired
    public ResponseController(ResponseService responseService, TicketService ticketService, UsersRepo usersRepo) {
        this.ticketService = ticketService;
        this.usersRepo = usersRepo;
        this.responseService = responseService;
    }

    // Create or update a response
    @PostMapping("/save")
    public ResponseEntity<Responses> saveResponse(@RequestBody ResponseRequest responseRequest) {
        // Validate that ticketId and userId are not null
        if (responseRequest.getTicketId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        if (responseRequest.getUserId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Fetch the ticket
        Tickets ticket = ticketService.getTicketById(responseRequest.getTicketId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + responseRequest.getTicketId()));

        // Fetch the user
        Users user = usersRepo.findById(responseRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + responseRequest.getUserId()));

        // Create a new Responses entity and set its fields
        Responses response = new Responses();
        response.setTicket(ticket);
        response.setUser(user);
        response.setResponseText(responseRequest.getResponseText());
        response.setRespondedAt(java.time.LocalDateTime.now());

        // Save the response
        responseService.saveResponse(response);
        return ResponseEntity.ok(response);
    }

    // Get all responses
    @GetMapping
    public ResponseEntity<List<Responses>> getAllResponses() {
        List<Responses> responses = responseService.getAllResponses();
        return ResponseEntity.ok(responses);
    }

    // Get responses by ticket ID
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Responses>> getResponsesByTicketId(@PathVariable UUID ticketId) {
        Optional<List<Responses>> responses = responseService.getResponsesByTicketId(ticketId);
        return responses.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get response by ID
    @GetMapping("/{id}")
    public ResponseEntity<Responses> getResponseById(@PathVariable int id) {
        Optional<Responses> response = responseService.getResponseById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete response by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable int id) {
        responseService.deleteResponseById(id);
        return ResponseEntity.noContent().build();
    }
}