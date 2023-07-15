package com.cognizant.training.bloodBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.training.bloodBank.model.Login;
import com.cognizant.training.bloodBank.model.User;
import com.cognizant.training.bloodBank.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	static Long curr_user;
	
	@PostMapping("/user/login")
	public User LoginUser(@RequestBody Login login) {
		User obj= userService.login(login);
		if(obj == null) {
			return null;
		}
		curr_user=obj.getUser_id();
		return obj;
	}
	
	@PostMapping("/user/logout")
	public Long LogoutUser() {
		curr_user=0L;
		return curr_user;
	}
	
	@PostMapping("/user/register")
	public User RegisterUser(@RequestBody User user) {
		return userService.register(user);
		
	}
	

}
