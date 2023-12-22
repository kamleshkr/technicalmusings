package com.technicalmusings.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.ringbuffer.Ringbuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Service
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final HazelcastInstance hzInstance;

    private final String ringBufferName;

    @Autowired
    public SearchService(
            HazelcastInstance hzInstance,
            @Value("${recentSearch.ringBuffer.name}") String ringBufferName) {
        this.hzInstance = hzInstance;
        this.ringBufferName = ringBufferName;
    }

    /**
     * Search for the specified query
     * @param query the search query
     * @return search result
     */
    public String searchForQuery(String query) {
        Ringbuffer<String> ringBuffer = hzInstance.getRingbuffer(ringBufferName);
        long sequence = ringBuffer.add(query);
        logger.info("Added query {} to recent search. Sequence {}", query, sequence);
        return "You searched for %s".formatted(query);
    }

    /**
     * @return recent search queries based on configured capacity
     */
    public Collection<String> getAllRecentSearches() throws InterruptedException {
        Ringbuffer<String> ringBuffer = hzInstance.getRingbuffer(ringBufferName);

        long headSequence = ringBuffer.headSequence();
        long tailSequence = ringBuffer.tailSequence();

        logger.info("Head sequence {} Tail sequence {}", headSequence, tailSequence);

        List<String> recentSearches = new ArrayList<>();

        while(headSequence <= tailSequence){
            String searchQuery = ringBuffer.readOne(headSequence);
            recentSearches.add(searchQuery);
            headSequence++;

        }
        return recentSearches;
    }
}
