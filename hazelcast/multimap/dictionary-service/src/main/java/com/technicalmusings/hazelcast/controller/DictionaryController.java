package com.technicalmusings.hazelcast.controller;

import com.technicalmusings.hazelcast.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService service;

    @Autowired
    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("/{word}")
    public Collection<String> getWordMeanings(@PathVariable String word) {
        return service.getWordMeanings(word);
    }

}
