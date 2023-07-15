package com.cognizant.training.bloodBank.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.Donor;
import com.cognizant.training.bloodBank.model.User;
import com.cognizant.training.bloodBank.repository.UserRepository;
import com.cognizant.training.bloodBank.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public User register(User user) {
		return userRepository.save(user);
	}

	@Override
	public User login(com.cognizant.training.bloodBank.model.Login login) {
		String tempEmail=login.getEmail();
		String tempPass= login.getPassword();
		if(tempEmail==null||tempPass==null)
			return null;
		User obj=userRepository.findByEmailAndPassword(tempEmail, tempPass);
		if(obj!=null && login.getRole()==obj.getRole())
			return obj;
		else return null;
	}


}
