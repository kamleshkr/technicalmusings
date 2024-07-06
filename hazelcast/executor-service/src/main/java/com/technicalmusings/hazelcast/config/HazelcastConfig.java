package com.technicalmusings.hazelcast.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
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

    @Bean
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        // Configure the priority queue
        ExecutorConfig executorConfig = config.getExecutorConfig("exec");
        executorConfig.setPoolSize( 1 ).setQueueCapacity( 10 )
                .setStatisticsEnabled( true )
                .setSplitBrainProtectionName( "splitbrainprotectionname" );

        config.addExecutorConfig(executorConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
