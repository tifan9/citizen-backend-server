package com.auth.backendserver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "responses")
public class Responses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Tickets ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "response_text", nullable = false, columnDefinition = "TEXT")
    private String responseText;

    @Column(name = "responded_at", nullable = false, updatable = false)
    private LocalDateTime respondedAt = LocalDateTime.now();

    public Responses() {
    }

    public Responses(int id, LocalDateTime respondedAt, String responseText, Tickets ticket, Users user) {
        this.id = id;
        this.respondedAt = respondedAt;
        this.responseText = responseText;
        this.ticket = ticket;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(LocalDateTime respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}