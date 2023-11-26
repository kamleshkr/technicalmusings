package com.technicalmusings.hazelcast.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.RingbufferConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Configuration
public class HazelcastConfig {

    @Value("${recentSearch.ringBuffer.name}")
    private String ringBufferName;

    @Value("${recentSearch.ringBuffer.capacity}")
    private int ringBufferCapacity;

    @Bean
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        // Configure the ring buffer
        RingbufferConfig ringbufferConfig = new RingbufferConfig(ringBufferName);
        ringbufferConfig.setCapacity(ringBufferCapacity);

        config.addRingBufferConfig(ringbufferConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
