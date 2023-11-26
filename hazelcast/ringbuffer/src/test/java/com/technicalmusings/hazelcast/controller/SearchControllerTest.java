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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SearchControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${recentSearch.ringBuffer.capacity}")
    private int ringBufferCapacity;

    @Test
    void recentSearchesShouldContainOnlyMaxAllowedResults() throws Exception {
        IntStream
                .range(0, 20)
                .forEach( i ->
                        Assertions.assertThat(
                                this.restTemplate.getForObject("http://localhost:" + port + "/search?query=searchString" + i,
                                String.class)
                        ).contains("You searched for")
                );

        @SuppressWarnings("unchecked")
        Collection<String> recentSearches = this.restTemplate.getForObject("http://localhost:" + port + "/search/recent",
                Collection.class);

        Assertions.assertThat(recentSearches.size()).isEqualTo(ringBufferCapacity);

    }

}