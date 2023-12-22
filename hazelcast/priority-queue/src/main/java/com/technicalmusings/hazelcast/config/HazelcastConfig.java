package com.technicalmusings.hazelcast.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.technicalmusings.hazelcast.model.TicketPriorityComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Configuration
public class HazelcastConfig {

    @Value("${ticket.queue.name}")
    private String ticketQueueName;

    @Bean
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        // Configure the priority queue
        QueueConfig queueConfig = config.getQueueConfig("default");
        queueConfig.setName(ticketQueueName)
                .setPriorityComparatorClassName(TicketPriorityComparator.class.getName());

        config.addQueueConfig(queueConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
