package com.technicalmusings.hazelcast.controller;

import com.technicalmusings.hazelcast.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService service;

    @Autowired
    public SearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping
    public String searchForQuery(@RequestParam String query) {
        return service.searchForQuery(query);
    }

    @GetMapping("/recent")
    public Collection<String> getAllRecentSearches() {
        try {
            return service.getAllRecentSearches();
        } catch (InterruptedException e) { // The readOne method may throw InterruptedException
            throw new RuntimeException(e);
        }
    }
}
