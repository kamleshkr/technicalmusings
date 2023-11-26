package com.technicalmusings.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HazelcastRingbufferApplicationTests {

	@Autowired
	private HazelcastInstance instance;

	@Test
	void contextLoads() {
		Assertions.assertThat(instance).isNotNull();
	}

}
