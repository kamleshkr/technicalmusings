package com.technicalmusings.hazelcast.controller;

import com.technicalmusings.hazelcast.model.Ticket;
import com.technicalmusings.hazelcast.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = service.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping
    public Collection<Ticket> getAllTickets() {
        return service.getAllTickets();
    }
}
