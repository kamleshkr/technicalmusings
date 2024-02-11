package com.technicalmusings.hazelcast.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MultiMapConfig;
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

    @Value("${dictionary.cache.name}")
    private String cacheName;

    @Bean
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        MultiMapConfig mmConfig
                = new MultiMapConfig()
                    .setName(cacheName)
                    .setValueCollectionType( "SET" );

        config.addMultiMapConfig(mmConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
