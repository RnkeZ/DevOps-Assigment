package com.mkern.infobip.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mkern.infobip.model.User;

@Repository
public class RedisUserRepository {
	private HashOperations<String, String, User> hashOperations;

	@Autowired
	RedisTemplate<String, ?> redisTemplate;

	public RedisUserRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public User save(User user) {
		hashOperations.put("USER", user.getId(), user);
		return user;
	}

	public List<User> findAll() {
		return hashOperations.values("USER");
	}

	public User findById(String id) {
		return (User) hashOperations.get("USER", id);
	}

	public void update(User user) {
		save(user);
	}

	public void delete(String id) {
		hashOperations.delete("USER", id);
	}

}
