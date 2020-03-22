package com.mkern.infobip;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mkern.infobip.config.TestRedisConfiguration;

@SpringBootTest(classes = TestRedisConfiguration.class)
class InfobipRedisServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
