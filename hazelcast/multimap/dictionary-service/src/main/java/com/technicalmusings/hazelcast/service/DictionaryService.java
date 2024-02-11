package com.technicalmusings.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.multimap.MultiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Service
public class DictionaryService {

    private final HazelcastInstance hzInstance;
    private final String dictionaryCacheName;

    @Autowired
    public DictionaryService(HazelcastInstance hzInstance, @Value("${dictionary.cache.name}") String dictionaryCacheName) {
        this.hzInstance = hzInstance;
        this.dictionaryCacheName = dictionaryCacheName;
    }

    public Collection<String> getWordMeanings(String word) {
        MultiMap<String, String> dictionaryCache =  hzInstance.getMultiMap(dictionaryCacheName);
        return dictionaryCache.get(word);
    }
}
