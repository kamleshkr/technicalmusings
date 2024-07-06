package com.technicalmusings.hazelcast.controller;

import com.technicalmusings.hazelcast.service.NameItService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@RestController
@RequestMapping("/exec")
public class NameItController {

    private final NameItService service;

    public NameItController(NameItService service) {
        this.service = service;
    }

    @GetMapping
    public void triggerExec() {
        service.triggerExec();
    }

}
