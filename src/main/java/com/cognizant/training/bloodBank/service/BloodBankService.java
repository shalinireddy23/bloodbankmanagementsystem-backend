package com.cognizant.training.bloodBank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cognizant.training.bloodBank.model.BloodBank;

@Service
public interface BloodBankService {
	
	BloodBank save(BloodBank bb);
	
	void delete(BloodBank bb);
	
	int deleteByEmail(String email);
	
	List<BloodBank> findAll();
	
	BloodBank findOneByEmail(String email);
	
	Optional<BloodBank> findOne(Long bbid);
	
	BloodBank login(BloodBank bb);
	
	List<BloodBank>  getBloodBankByLocation(String location);

}
