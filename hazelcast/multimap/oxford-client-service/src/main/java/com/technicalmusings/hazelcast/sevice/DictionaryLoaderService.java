package com.technicalmusings.hazelcast.sevice;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.multimap.MultiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Service
public class DictionaryLoaderService implements ApplicationRunner {

    @Autowired
    private HazelcastInstance hzInstance;

    @Value("${dictionary.cache.name}")
    private String dictionaryCacheName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MultiMap<String, String> dictionaryCache = hzInstance.getMultiMap(dictionaryCacheName);
        dictionaryCache.put("ubiquitous", "omnipresent");
        dictionaryCache.put("abridge", "condense");
        dictionaryCache.put("abridge", "shorten");
        dictionaryCache.put("concede", "acknowledge");
    }
}
