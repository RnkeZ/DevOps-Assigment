package com.mkern.infobip.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkern.infobip.model.User;
import com.mkern.infobip.repositories.RedisUserRepository;

@Service
public class UserService {
	String[] firstnames = { "George", "John", "Thomas", "James", "Andrew", "Martin", "Thomas" };
	String[] lastnames = { "Adams", "Jefferson", "Jackson", "Henry", "Van Buren", "Harrison", "Tyler", "Henry",
			"Washington", };

	@Autowired
	RedisUserRepository redisUserRepository;

	public User createRandomUser(Integer id) {
		Random generate = new Random();
		User user = new User();
		user.setId(id.toString());
		user.setFirstname(firstnames[generate.nextInt(firstnames.length)]);
		user.setLastname(lastnames[generate.nextInt(lastnames.length)]);
		user.setUsername((user.getFirstname().substring(0, 1) + user.getLastname()).toLowerCase());
		return user;
	}

	public void create10000() {
		List<User> allUsers = redisUserRepository.findAll();
		int currentMaxId = allUsers.isEmpty() ? 1 : Integer.valueOf(allUsers.get(allUsers.size() - 1).getId());
		int counter = 0;
		while (counter < 10000) {
			redisUserRepository.save(createRandomUser(currentMaxId + counter));
			counter++;
		}

	}

}
