package com.technicalmusings.hazelcast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = -175102428087953335L;
    private String id;
    private String name;
    private Short priority;
}
