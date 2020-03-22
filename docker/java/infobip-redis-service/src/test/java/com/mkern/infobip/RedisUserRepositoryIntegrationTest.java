package com.mkern.infobip;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mkern.infobip.config.TestRedisConfiguration;
import com.mkern.infobip.model.User;
import com.mkern.infobip.repositories.RedisUserRepository;
import com.mkern.infobip.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class RedisUserRepositoryIntegrationTest {

	@Autowired
	private RedisUserRepository redisUserRepository;

	@Autowired
	private UserService userService;

	@Test
	public void shouldSaveUser_toRedis() {

		User user = userService.createRandomUser(1);

		User saved = redisUserRepository.save(user);

		assertNotNull(saved);
	}
}
