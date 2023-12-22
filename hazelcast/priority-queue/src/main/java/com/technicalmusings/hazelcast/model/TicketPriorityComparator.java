package com.technicalmusings.hazelcast.model;

import java.util.Comparator;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
public class TicketPriorityComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket first, Ticket second) {
        return first.getPriority().compareTo(second.getPriority());
    }
}
