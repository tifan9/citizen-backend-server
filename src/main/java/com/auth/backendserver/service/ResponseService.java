package com.auth.backendserver.service;

import com.auth.backendserver.model.Responses;
import com.auth.backendserver.repo.ResponseRepo;
import com.auth.backendserver.repo.TicketRepo;
import com.auth.backendserver.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResponseService {

    private final ResponseRepo responseRepo;
    private final TicketRepo ticketRepo;
    private final UsersRepo usersRepo;
    @Autowired
    public ResponseService(ResponseRepo responseRepo, TicketRepo ticketRepo, UsersRepo usersRepo) {
        this.ticketRepo = ticketRepo;
        this.usersRepo = usersRepo;
        this.responseRepo = responseRepo;
    }

    // Create or update a response
    public void saveResponse(Responses response) {
        if(response != null){
            // Save the response
            responseRepo.save(response);
        }
    }

    // Retrieve all responses
    public List<Responses> getAllResponses() {
        return responseRepo.findAll();
    }

    // Retrieve responses by ticket ID
    public Optional<List<Responses>> getResponsesByTicketId(UUID ticketId) {
        return responseRepo.findByTicket_TicketId(ticketId);
    }

    // Retrieve a response by ID
    public Optional<Responses> getResponseById(int id) {
        return responseRepo.findById(id);
    }

    // Delete a response by ID
    public void deleteResponseById(int id) {
        responseRepo.deleteById(id);
    }
}