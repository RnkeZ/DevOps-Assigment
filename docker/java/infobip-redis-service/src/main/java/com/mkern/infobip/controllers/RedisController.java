package com.mkern.infobip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkern.infobip.model.User;
import com.mkern.infobip.services.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api")
@Api(value = "Redis REST controller", tags = { "Redis" })
public class RedisController {

	@Autowired
	UserService userService;

	@GetMapping("/get-random-user")
	public User getCurrentUser() {
		return userService.createRandomUser(null);
	}

	@PostMapping("/create-10000-random-users")
	public void create10000() {
		userService.create10000();
	}

}
