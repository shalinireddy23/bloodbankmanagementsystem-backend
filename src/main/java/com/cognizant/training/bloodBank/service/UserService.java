package com.cognizant.training.bloodBank.service;

import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.Login;
import com.cognizant.training.bloodBank.model.User;

@Service
public interface UserService {

	User login(Login login);
	User register(User user);
}
