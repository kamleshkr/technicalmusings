package com.technicalmusings.hazelcast.service;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.technicalmusings.hazelcast.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Service
@Slf4j
public class TicketService {

    private final HazelcastInstance hzInstance;

    private final String ticketQueueName;

    @Autowired
    public TicketService(
            HazelcastInstance hzInstance,
            @Value("${ticket.queue.name}") String ticketQueueName
    ) {
        this.hzInstance = hzInstance;
        this.ticketQueueName = ticketQueueName;
    }

    /**
     * Create ticket and add in queue for processing
     * @param ticket object of ticket
     * @return the created ticket object
     */
    public Ticket createTicket(Ticket ticket) {
        IQueue<Ticket> ticketQueue = hzInstance.getQueue( ticketQueueName );
        ticket.setId(UUID.randomUUID().toString());
        ticketQueue.offer(ticket);
        return ticket;
    }

    /**
     * Get all tickets from queue for processing
     * @return list of tickets
     */
    public Collection<Ticket> getAllTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        IQueue<Ticket> ticketQueue = hzInstance.getQueue( ticketQueueName );
        while(!ticketQueue.isEmpty()) {
            tickets.add(ticketQueue.poll());
        }
        return tickets;
    }
}
