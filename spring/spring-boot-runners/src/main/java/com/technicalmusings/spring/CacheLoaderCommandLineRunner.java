package com.technicalmusings.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Component
@Order(2)
public class CacheLoaderCommandLineRunner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CacheLoaderCommandLineRunner.class);

    @Override
    public void run(String... args) {
        logger.info("------- Inside command line runner. Argument {}", args[0]);
        // extract cacheSize value from the string argument
        // ... code to load the cache
    }
}