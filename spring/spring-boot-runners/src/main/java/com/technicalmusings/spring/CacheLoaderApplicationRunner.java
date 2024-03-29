package com.technicalmusings.spring;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Component
@Order(1)
public class CacheLoaderApplicationRunner implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(CacheLoaderApplicationRunner.class);

    @PostConstruct
    public void init() {
        logger.info("------- Inside post construct");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (Objects.isNull(args.getOptionValues("cacheSize"))) {
            throw new IllegalArgumentException("cacheSize argument is not specified");
        }
        String cacheSize = args.getOptionValues("cacheSize").getFirst();
        logger.info("------- Inside application runner. Cache Size = {}", cacheSize);
        // ... code to load the cache
    }
}
