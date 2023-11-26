package com.technicalmusings.hazelcast.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Collection;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SearchControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${recentSearch.ringBuffer.capacity}")
    private int ringBufferCapacity;

    @Test
    void recentSearchesShouldContainOnlyMaxAllowedResults() {
        IntStream
            .range(0, 20)
            .forEach(i ->
                assertThat(
                    this.restTemplate.getForObject(
                        "http://localhost:%d/search?query=searchString%d".formatted(port, i),
                        String.class
                    )
                ).contains("You searched for")
            );

        @SuppressWarnings("unchecked")
        Collection<String> recentSearches =
            this.restTemplate.getForObject(
              "http://localhost:%d/search/recent".formatted(port),
              Collection.class
            );

        assertThat(recentSearches.size()).isEqualTo(ringBufferCapacity);

    }

}